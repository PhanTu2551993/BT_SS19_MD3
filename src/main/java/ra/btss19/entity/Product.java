package ra.btss19.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "product")
@Builder
public class Product {
    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;
    @Column(name = "pro_name")
    @NotEmpty(message = "Name is Empty!")
    private String proName;
    @Column(name = "price")
    @NotNull(message = "Price is Null!")
    private Double price;
    @Column(name = "stock")
    @NotNull(message = "Stock is Null!")
    private Integer stock;
    @Column(name = "pro_status")
    @NotNull(message = "Status is Null!")
    private Boolean proStatus;
    @Column(name = "pro_imageUrl")
    @NotEmpty(message = "Image is Empty!")
    private String proImage;
    @ManyToOne
    @JoinColumn(name = "cat_id",referencedColumnName = "id")
    private Category cat;

}
