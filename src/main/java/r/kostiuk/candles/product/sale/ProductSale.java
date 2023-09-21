package r.kostiuk.candles.product.sale;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Many-to-many association (Product-Sale) that holds extra columns
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "product_sales")
public class ProductSale implements Serializable {

    @EmbeddedId
    private ProductSaleId id;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_price", columnDefinition = "numeric(12, 2)")
    private BigDecimal productPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSale productSale)) {
            return false;
        }
        return Objects.equals(id, productSale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
