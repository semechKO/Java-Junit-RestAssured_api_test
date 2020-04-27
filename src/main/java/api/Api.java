package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import config.PropertiesProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static serializer.JsonSerializer.formatJson;
import static serializer.JsonSerializer.getJson;

public abstract class Api {
    private static Logger logger = LoggerFactory.getLogger(Api.class);

    @Setter
    private JsonInclude.Include inclusion = JsonInclude.Include.NON_NULL;

    @Setter
    @Getter
    private String token;

    @Setter
    private boolean skipAuthorization = false;

    /**
     * Base RestAssured instance initialization
     *
     * @return RestAssured instance
     */
    protected RequestSpecification defaultSpec() {
        RequestSpecification requestSpecification = given()
                .baseUri(PropertiesProvider.getUrl())
                .header("accept-language", "ru")
                .contentType("application/json;charset=UTF-8");
        return requestSpecification;
    }

    /**
     * Sends requests, using base RestAssured instance
     *
     * @param endpoint   - feature URL
     * @param body       - request body
     * @param restMethod - using REST-method
     * @return - server response
     */
    protected Response sendRequest(String endpoint, Object body, RestMethods restMethod) {
        RequestSpecification requestSpecification = defaultSpec().contentType(ContentType.JSON);
        return sendRequest(requestSpecification, endpoint, body, restMethod);
    }

    /**
     * Sends requests without body, using base RestAssured instance
     *
     * @param endpoint   - feature URL
     * @param restMethod - using REST-method
     * @return - server response
     */
    protected Response sendRequest(String endpoint, RestMethods restMethod) {
        RequestSpecification requestSpecification = defaultSpec().contentType(ContentType.JSON);
        return sendRequest(requestSpecification, endpoint, null, restMethod);
    }

    /**
     * Sends requests without body using custom RestAssured instance
     *
     * @param requestSpecification - custom RestAssured instance
     * @param endpoint             - feature URL
     * @param restMethod           - using REST-method
     * @return - server response
     */
    protected Response sendRequest(RequestSpecification requestSpecification, String endpoint, RestMethods restMethod) {
        return sendRequest(requestSpecification, endpoint, null, restMethod);
    }

    /**
     * Sends requests, using custom RestAssured instance
     *
     * @param requestSpecification - custom RestAssured instance
     * @param endpoint             - feature URL
     * @param body                 - request body
     * @param restMethod           - using REST-method
     * @return - server response
     */
    @Step("Send {3} request")
    protected Response sendRequest(RequestSpecification requestSpecification, String endpoint, Object body, RestMethods restMethod) {
        logRequest(requestSpecification, restMethod, endpoint);

        requestSpecification = getRequestBody(body, requestSpecification);

        Response response = null;
        switch (restMethod) {
            case GET: {
                response = requestSpecification.get(endpoint);
                break;
            }
            case POST: {
                response = requestSpecification.post(endpoint);
                break;
            }
            case PUT: {
                response = requestSpecification.put(endpoint);
                break;
            }
            case DELETE: {
                response = requestSpecification.delete(endpoint);
                break;
            }
        }

        String responseBody = formatJson(response.body().asString());
        logger.info("Response code: " + response.getStatusCode());
        logger.info("Response body:\n" + responseBody);
        Allure.addAttachment("Response body", "application/json", responseBody);
        return response;
    }

    /**
     * Logs http-request
     * Query parameters included
     *
     * @param requestSpecification - RestAssured construction
     * @param restMethod           - Selected REST-method
     * @param url                  - absolute url
     */
    private void logRequest(RequestSpecification requestSpecification, RestMethods restMethod, String url) {
        RequestSpecificationImpl specImpl = (RequestSpecificationImpl) requestSpecification;
        String absoluteUrl = specImpl.getURI(specImpl.partiallyApplyPathParams(url, false, null));
        Allure.addAttachment("URL", absoluteUrl);
        logger.info(restMethod.toString() + ":\t" + absoluteUrl);
        if (!specImpl.getRequestParams().isEmpty()) {
            logger.info("PARAMS: " + specImpl.getRequestParams());
        }
    }

    /**
     * Makes JSON from object
     * Adds json-string to request's body
     * If object is null, all actions are ignoring and RestAssured instance with return without changes
     *
     * @param body                 - object for JSON-serialization
     * @param requestSpecification - RestAssured instance
     * @return - RestAssured instance with body
     */
    private RequestSpecification getRequestBody(Object body, RequestSpecification requestSpecification) {
        if (body != null) {
            String json = getJson(body, inclusion);
            requestSpecification.body(json);
            json = formatJson(json);
            logger.info("Request body:\n" + json);
            Allure.addAttachment("Request body", "application/json", json);
        }
        return requestSpecification;
    }

    /**
     * Encodes value to URL
     *
     * @param value - encoding value
     * @return - encoded value (or default, if something goes wrong)
     */
    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return value;
    }
}
