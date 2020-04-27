package openapi.autotest.login;

import api.login.LoginApi;
import dto.login.LoginRequestDTO;
import openapi.autotest.TestBase;
import validation.login.LoginMatcher;

public abstract class LoginTestBase extends TestBase {

    protected static LoginApi api = new LoginApi();
    protected static LoginMatcher matcher = new LoginMatcher();

    public LoginRequestDTO createLoginRequest(String email, String password) {
        return new LoginRequestDTO(email, password);
    }

    public LoginRequestDTO createLoginRequestWithMail(String email) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(email);
        return loginRequestDTO;
    }

    public LoginRequestDTO createLoginRequestWithPassword(String password) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setPassword(password);
        return loginRequestDTO;
    }
}
