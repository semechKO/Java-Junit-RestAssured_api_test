package validation.user;

import dto.user.UserDTO;
import io.qameta.allure.Step;
import validation.BaseMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMatcher extends BaseMatcher {

    /**
     * Checks analytic group
     * @param expected - expected group
     * @param actual - actual group
     */
    @Step("Check analytic group")
    public void assertUser(UserDTO expected, UserDTO actual) {
        //Data
        assertEquals(expected.getData().getId(), actual.getData().getId(), "Id mismatch");
        assertEquals(expected.getData().getEmail(), actual.getData().getEmail(), "Email mismatch");
        assertEquals(expected.getData().getFirst_name(), actual.getData().getFirst_name(), "First name mismatch");
        assertEquals(expected.getData().getLast_name(), actual.getData().getLast_name(), "Last name mismatch");
        assertEquals(expected.getData().getAvatar(), actual.getData().getAvatar(), "Avatar mismatch");

        //Ad
        assertEquals(expected.getAd().getCompany(), actual.getAd().getCompany(), "Company mismatch");
        assertEquals(expected.getAd().getUrl(), actual.getAd().getUrl(), "Url mismatch");
        assertEquals(expected.getAd().getText(), actual.getAd().getText(), "Text name mismatch");
    }

}
