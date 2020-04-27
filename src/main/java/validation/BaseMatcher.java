package validation;

import api.ResponseCodes;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class BaseMatcher {

    /**
     * Performs response code assertion
     * @param expectedCode - expected response code
     * @param actualResponse - response from server
     */
    @Step("Check response code is \"{0}\"")
    public void assertResponseCode(ResponseCodes expectedCode, Response actualResponse) {
        assertEquals(expectedCode.getCode(), actualResponse.getStatusCode(), "Response code mismatch");
    }

    /**
     * Performs response code assertion
     * @param fieldPath - expected field path
     * @param expectedObj - expected response code
     * @param actualResponse - response from server
     */
    @Step("Check field by path \"{0}\" equals \"{1}\"")
    public void assertFieldInResponseBody(String fieldPath, Object expectedObj, Response actualResponse) {
        actualResponse.then().assertThat().body(fieldPath, equalTo(expectedObj));
    }

    /**
     * Check if response code success
     * @param response - response from server
     */
    public void assertSuccessResponse(Response response) {
        assertResponseCode(ResponseCodes.OK, response);
    }

    /**
     * Check if response code is BadRequest
     * @param response - response from server
     */
    public void assertBadRequest(Response response) {
        assertResponseCode(ResponseCodes.BAD_REQUEST, response);
    }

    /**
     * Check if response code is NotFound
     * @param response - response from server
     */
    public void assertNotFoundRequest(Response response) {
        assertResponseCode(ResponseCodes.NOT_FOUND, response);
    }


    /**
     * Check if response code is NotFound
     * @param response - response from server
     */
    public void assertBodyIsEmpty(Response response) {
        assertEquals("{}", response.getBody().asString());
    }


}

