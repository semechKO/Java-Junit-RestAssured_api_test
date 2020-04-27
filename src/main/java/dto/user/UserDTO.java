package dto.user;

import dto.DataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends DataTransferObject {
    private UserDataDTO data;
    private UserAdDTO ad;
}
