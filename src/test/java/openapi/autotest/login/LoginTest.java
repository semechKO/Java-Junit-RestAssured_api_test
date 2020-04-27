package openapi.autotest.login;

import dto.login.LoginRequestDTO;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LoginTest extends LoginTestBase{

    @Story("Smoke")
    @DisplayName("Send valid login request")
    @Test
    public void sendValidLoginRequest() {
        LoginRequestDTO loginRequestDTO = createLoginRequest("eve.holt@reqres.in",
                "pistol");
        Response response = api.sendLoginRequest(loginRequestDTO);
        matcher.assertSuccessResponse(response);
        matcher.assertToken("QpwL5tke4Pnpja7X4",response);
    }

    private static Stream<Arguments> invalidLoginProvider() {
        // description, body
        return Stream.of(
                Arguments.of("Valid email and invalid password", new LoginRequestDTO("eve.holt@reqres.in",
                        "wrongPass")),

                Arguments.of("Invalid email and valid password", new LoginRequestDTO("wrong@mail",
                        "cityslicka")),

                Arguments.of("Empty request", new LoginRequestDTO())
        );
    }

    @DisplayName("Send invalid login request")
    @ParameterizedTest(name = "{0}")
    @MethodSource(value = "invalidLoginProvider")
    public void sendInvalidLoginRequest(String description, LoginRequestDTO loginRequestDTO) {
        Response response = api.sendLoginRequest(loginRequestDTO);
        matcher.assertBadRequest(response);
    }

    @Story("Smoke")
    @DisplayName("Send only email for login request")
    @Test
    public void sendLoginRequestOnlyMail() {
        LoginRequestDTO loginRequestDTO = createLoginRequestWithMail("eve.holt@reqres.in");
        Response response = api.sendLoginRequest(loginRequestDTO);
        matcher.assertBadRequest(response);
    }

    @Story("Smoke")
    @DisplayName("Send only pass for login request")
    @Test
    public void sendLoginRequestOnlyPass() {
        LoginRequestDTO loginRequestDTO = createLoginRequestWithPassword("cityslicka");
        Response response = api.sendLoginRequest(loginRequestDTO);
        matcher.assertBadRequest(response);
    }
}
