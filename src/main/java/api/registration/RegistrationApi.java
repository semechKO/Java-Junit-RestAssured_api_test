package api.registration;

import api.Api;
import api.RestMethods;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RegistrationApi extends Api {

    private static final String REGISTER_URL = "api/register";

    /**
     * Send register request
     * @param body - request body
     * @return - response from server
     */
    @Step("Send register request")
    public Response sendRegisterRequest(Object body) {
        Response response = sendRequest(REGISTER_URL, body, RestMethods.POST);
        return response;
    }
}
