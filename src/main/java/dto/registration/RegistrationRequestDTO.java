package dto.registration;

import dto.DataTransferObject;
import lombok.Data;

@Data
public class RegistrationRequestDTO extends DataTransferObject {

    private String email;
    private String password;

    public RegistrationRequestDTO() {

    }

    public RegistrationRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
