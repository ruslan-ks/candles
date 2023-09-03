package r.kostiuk.candles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import r.kostiuk.candles.entity.id.ProductSaleId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "product_price", columnDefinition = "numeric(12, 2)")
    private BigDecimal productPrice;

    @Column(name = "product_count")
    private Integer productCount;

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