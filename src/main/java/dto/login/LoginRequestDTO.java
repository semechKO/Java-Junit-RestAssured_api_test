package dto.login;

import dto.DataTransferObject;
import lombok.Data;

@Data
public class LoginRequestDTO extends DataTransferObject {

    private String email;
    private String password;

    public LoginRequestDTO() {

    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
