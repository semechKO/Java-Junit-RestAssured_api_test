package openapi.autotest.user;

import api.user.UserApi;
import dto.user.UserAdDTO;
import dto.user.UserDTO;
import dto.user.UserDataDTO;
import openapi.autotest.TestBase;
import validation.user.UserMatcher;

public class UserTestBase extends TestBase {

    protected static UserApi api = new UserApi();
    protected static UserMatcher matcher = new UserMatcher();

    public UserDTO getExpectedUserDTO() {
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setId(2);
        userDataDTO.setEmail("janet.weaver@reqres.in");
        userDataDTO.setFirst_name("Janet");
        userDataDTO.setLast_name("Weaver");
        userDataDTO.setAvatar("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
        UserAdDTO userAdDTO = new UserAdDTO();
        userAdDTO.setCompany("StatusCode Weekly");
        userAdDTO.setUrl("http://statuscode.org/");
        userAdDTO.setText("A weekly newsletter focusing on software development, infrastructure, the server, performance, " +
                "and the stack end of things.");

        return new UserDTO(userDataDTO, userAdDTO);

    }
}
