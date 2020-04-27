package openapi.autotest.priority.example;

import openapi.autotest.login.LoginTest;
import openapi.autotest.registration.RegistrationTest;
import openapi.autotest.user.UserTest;
import org.junit.jupiter.api.Test;

public class PriorityTest {

    LoginTest loginTest = new LoginTest();
    UserTest userTest = new UserTest();
    RegistrationTest registrationTest = new RegistrationTest();

    @Test
    public void criticalPriorityTest() {
        loginTest.sendValidLoginRequest();
        ///Расположил бы этот тест, но уже времени мало и с дата провайдером мука, хотя можно расскидать его на мелкие
        // loginTest.sendInvalidLoginRequest();
        registrationTest.sendValidRegistrationRequest();
        // Здесь так же
        //registrationTest.sendInvalidRegistrationRequest();
        userTest.sendValidGetUserRequest();
    }

    @Test
    public void majorPriorityTest() {
        loginTest.sendLoginRequestOnlyMail();
        loginTest.sendLoginRequestOnlyPass();
        registrationTest.sendRegistrationRequestOnlyMail();
        registrationTest.sendRegistrationRequestOnlyPass();
        userTest.sendInvalidGetUserRequest();
    }
}
