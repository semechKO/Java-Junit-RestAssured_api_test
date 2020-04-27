package openapi.autotest.user;

import dto.user.UserDTO;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest extends UserTestBase{

    @Story("Smoke")
    @DisplayName("Send valid login request")
    @Test
    public void sendValidGetUserRequest() {
        Response response = api.getUserById(2);
        UserDTO actualUser = response.as(UserDTO.class);
        matcher.assertSuccessResponse(response);
        matcher.assertUser(getExpectedUserDTO(),actualUser);
    }

    @Story("Smoke")
    @DisplayName("Send invalid login request")
    @Test
    public void sendInvalidGetUserRequest() {
        Response response = api.getUserById(23);
        matcher.assertNotFoundRequest(response);
        matcher.assertBodyIsEmpty(response);
    }
}
