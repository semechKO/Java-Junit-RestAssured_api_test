package dto.user;

import dto.DataTransferObject;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDataDTO extends DataTransferObject {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
