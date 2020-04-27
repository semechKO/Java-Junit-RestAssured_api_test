package dto.user;

import dto.DataTransferObject;
import lombok.Builder;
import lombok.Data;

@Data
public class UserAdDTO extends DataTransferObject {
    private String company;
    private String url;
    private String text;
}
