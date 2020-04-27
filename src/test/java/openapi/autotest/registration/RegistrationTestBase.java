package openapi.autotest.registration;

import api.registration.RegistrationApi;
import dto.registration.RegistrationRequestDTO;
import openapi.autotest.TestBase;
import validation.registration.RegistrationMatcher;

public abstract class RegistrationTestBase extends TestBase {

    protected static RegistrationApi api = new RegistrationApi();
    protected static RegistrationMatcher matcher = new RegistrationMatcher();

    public RegistrationRequestDTO createRegistrationRequest(String email, String password) {
        return new RegistrationRequestDTO(email, password);
    }

    public RegistrationRequestDTO createRegistrationRequestWithMail(String email) {
        RegistrationRequestDTO registrationRequestDTO = new RegistrationRequestDTO();
        registrationRequestDTO.setEmail(email);
        return registrationRequestDTO;
    }

    public RegistrationRequestDTO createRegistrationRequestWithPassword(String password) {
        RegistrationRequestDTO registrationRequestDTO = new RegistrationRequestDTO();
        registrationRequestDTO.setPassword(password);
        return registrationRequestDTO;
    }
}
