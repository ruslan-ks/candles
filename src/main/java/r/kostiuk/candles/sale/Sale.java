package r.kostiuk.candles.sale;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import r.kostiuk.candles.product.sale.ProductSale;
import r.kostiuk.candles.product.sale.ProductSaleId;
import r.kostiuk.candles.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private Long id;

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

    /**
     * Use current product.price
     */
    public void addProductSale(Product product, int productCount) {
        addProductSale(product, productCount, product.getPrice());
    }

    public void addProductSale(Product product, int productCount, BigDecimal productPrice) {
        ProductSale productSale = buildProductSale(product, productCount, productPrice);
        product.getProductSales().add(productSale);
        this.productSales.add(productSale);
    }

    private ProductSale buildProductSale(Product product, int productCount, BigDecimal productPrice) {
        var productSaleId = new ProductSaleId(product, this);
        return new ProductSale(productSaleId, productCount, productPrice);
    }
}
