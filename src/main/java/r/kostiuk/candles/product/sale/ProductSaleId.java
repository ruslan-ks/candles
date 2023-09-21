package r.kostiuk.candles.product.sale;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import r.kostiuk.candles.product.Product;
import r.kostiuk.candles.sale.Sale;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProductSaleId implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Sale sale;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSaleId id)) {
            return false;
        }
        return Objects.equals(product, id.product) && Objects.equals(sale, id.sale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, sale);
    }

    @Override
    public String toString() {
        return "ProductSaleId{" +
                "product.id=" + product.getId() +
                ", sale.id=" + sale.getId() +
                '}';
    }
}
