package openapi.autotest.registration;

import dto.registration.RegistrationRequestDTO;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Feature("Registration")
public class RegistrationTest extends RegistrationTestBase {

    @Story("Smoke")
    @DisplayName("Send valid registration request")
    @Test
    public void sendValidRegistrationRequest() {
        RegistrationRequestDTO registrationRequestDTO = createRegistrationRequest("eve.holt@reqres.in",
                "cityslicka");
        Response response = api.sendRegisterRequest(registrationRequestDTO);
        matcher.assertSuccessResponse(response);
        matcher.assertToken("QpwL5tke4Pnpja7X4", response);
        matcher.assertId(4, response);
    }

    private static Stream<Arguments> invalidRegistrationProvider() {
        // description, body
        return Stream.of(
                Arguments.of("Valid email and invalid password", new RegistrationRequestDTO("eve.holt@reqres.in",
                        "wrongPass")),

                Arguments.of("Invalid email and valid password", new RegistrationRequestDTO("wrong@mail",
                        "cityslicka")),

                Arguments.of("Empty request", new RegistrationRequestDTO())
        );
    }

    @DisplayName("Send invalid registration request")
    @ParameterizedTest(name = "{0}")
    @MethodSource(value = "invalidRegistrationProvider")
    public void sendInvalidRegistrationRequest(String description, RegistrationRequestDTO registrationRequestDTO) {
        Response response = api.sendRegisterRequest(registrationRequestDTO);
        matcher.assertBadRequest(response);
    }

    @Story("Smoke")
    @DisplayName("Send only email for registration request")
    @Test
    public void sendRegistrationRequestOnlyMail() {
        RegistrationRequestDTO registrationRequestDTO = createRegistrationRequestWithMail("eve.holt@reqres.in");
        Response response = api.sendRegisterRequest(registrationRequestDTO);
        matcher.assertBadRequest(response);
        matcher.assertMissingPassError(response);
    }

    @Story("Smoke")
    @DisplayName("Send only pass for registration request")
    @Test
    public void sendRegistrationRequestOnlyPass() {
        RegistrationRequestDTO registrationRequestDTO = createRegistrationRequestWithPassword("cityslicka");
        Response response = api.sendRegisterRequest(registrationRequestDTO);
        matcher.assertBadRequest(response);
    }
}
