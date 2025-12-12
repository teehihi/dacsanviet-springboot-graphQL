package LapTrinhWeb.SpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String images;
    private List<UserSimpleDTO> users;
    private List<ProductSimpleDTO> products;

    public CategoryDTO(Long id, String name, String images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class UserSimpleDTO {
    private Long id;
    private String fullname;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductSimpleDTO {
    private Long id;
    private String title;
    private Double price;
}