package api.login;

import api.Api;
import api.RestMethods;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class LoginApi extends Api {

    private static final String LOGIN_URL = "api/login";

    /**
     * Send register request
     *
     * @param body - request body
     * @return - response from server
     */
    @Step("Send login request")
    public Response sendLoginRequest(Object body) {
        Response response = sendRequest(LOGIN_URL, body, RestMethods.POST);
        return response;
    }
}
