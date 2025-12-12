package LapTrinhWeb.SpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullname;
    private String email;
    private String phone;
    private List<CategorySimpleDTO> categories;
    private List<ProductSimpleDTO> products;

    public UserDTO(Long id, String fullname, String email, String phone) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CategorySimpleDTO {
    private Long id;
    private String name;
}