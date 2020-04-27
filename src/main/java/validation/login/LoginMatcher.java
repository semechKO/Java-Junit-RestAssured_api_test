package validation.login;

import io.restassured.response.Response;
import validation.BaseMatcher;

public class LoginMatcher extends BaseMatcher {
    /**
     * Checks that token is as expected
     * @param token - expected token
     * @param actualResponse - response object
     */
    public void assertToken(String token, Response actualResponse) {
        assertFieldInResponseBody("token", token, actualResponse);
    }
}
