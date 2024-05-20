package ra.btss19.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer proId;
    @NotEmpty(message = "Name is Empty!")
    private String proName;
    @NotNull(message = "Price is Null!")
    private Double price;
    @NotNull(message = "Stock is Null!")
    private Integer stock;
    @NotNull(message = "Status is Null!")
    private Boolean proStatus;
    @NotNull(message = "Image is Null!")
    private MultipartFile proImage;
    @NotNull(message = "Category is Null!")
    private Integer catId;
}
