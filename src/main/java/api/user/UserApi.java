package api.user;

import api.Api;
import api.RestMethods;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApi extends Api {

    private static final String GET_USER_URL = "/api/users/{userId}";

    public Response getUserById(Object userId) {
        RequestSpecification requestSpecification = defaultSpec().pathParam("userId", userId);
        Response response = sendRequest(requestSpecification, GET_USER_URL, RestMethods.GET);
        return response;
    }
}
