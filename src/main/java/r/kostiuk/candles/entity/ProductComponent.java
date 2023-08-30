package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.*;
import r.kostiuk.candles.entity.id.ProductComponentId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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
