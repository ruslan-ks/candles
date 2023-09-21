package r.kostiuk.candles.entity.id;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import r.kostiuk.candles.product.Product;
import r.kostiuk.candles.component.Component;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProductComponentId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private Component component;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductComponentId id)) {
            return false;
        }
        return Objects.equals(product, id.product) && Objects.equals(component, id.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, component);
    }

    @Override
    public String toString() {
        return "CandleComponentId{" +
                "product.id=" + product.getId() +
                ", component.id=" + component.getId() +
                '}';
    }
}