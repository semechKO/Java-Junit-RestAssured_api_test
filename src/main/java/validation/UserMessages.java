package validation;

import lombok.Getter;


public class UserMessages {
    private static final String MISSING_PASSWORD = "Missing password";

    public String getMissingPassword() {
        return MISSING_PASSWORD;
    }
}
