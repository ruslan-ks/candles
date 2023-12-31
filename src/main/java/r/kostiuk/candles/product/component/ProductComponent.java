package r.kostiuk.candles.product.component;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Many-to-many association (Product-Component) that holds extra column (requiredAmount)
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_components")
public class ProductComponent implements Serializable {

    @EmbeddedId
    private ProductComponentId id;

    // Component amount required to create the product
    @Column(name = "required_amount", columnDefinition = "numeric(12, 3)")
    private BigDecimal requiredAmount;

    public ProductComponent(ProductComponentId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductComponent that)) {
            return false;
        }
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
