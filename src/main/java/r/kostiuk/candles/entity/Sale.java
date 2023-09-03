package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import r.kostiuk.candles.entity.id.ProductSaleId;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sales")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sold_on")
    private LocalDate soldOn;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.sale")
    private Set<ProductSale> productSales = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sale sale)) {
            return false;
        }
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Set<ProductSale> getProductSales() {
        return Collections.unmodifiableSet(productSales);
    }

    public void addProductSale(Product product, int productCount) {
        var productSaleId = new ProductSaleId(product, this);
        var productSale = new ProductSale(productSaleId, product.getPrice(), productCount);
        this.productSales.add(productSale);
    }
}
