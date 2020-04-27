package validation.registration;

import io.restassured.response.Response;
import validation.BaseMatcher;
import validation.UserMessages;

public class RegistrationMatcher extends BaseMatcher {
    /**
     * Checks that token is as expected
     * @param token - expected token
     * @param actualResponse - response object
     */
    public void assertToken(String token, Response actualResponse) {
        assertFieldInResponseBody("token", token, actualResponse);
    }

    /**
     * Checks that id is as expected
     * @param id - expected id
     * @param actualResponse - response object
     */
    public void assertId(int id, Response actualResponse) {
        assertFieldInResponseBody("id", id, actualResponse);
    }

    /**
     * Checks missing password msg
     * @param actualResponse - response object
     */
    public void assertMissingPassError(Response actualResponse) {
        assertFieldInResponseBody("error", new UserMessages().getMissingPassword(), actualResponse);
    }
}
