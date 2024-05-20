package ra.btss19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table (name = "category")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cat_name")
    @NotEmpty(message = "Name is Empty!")
    private String catName;
    @Column(name = "cat_status")
    @NotNull(message = "Status is Empty!")
    private Boolean catStatus;

    @OneToMany(mappedBy = "cat")
    private Set<Product> products;
}
