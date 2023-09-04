package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import r.kostiuk.candles.entity.id.ProductComponentId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @Column(name = "price", columnDefinition = "numeric(12, 2)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_id")
    private Product baseProduct;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "baseProduct")
    private Set<Product> derivedProducts = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<ProductComponent> productComponents = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<ProductSale> productSales = new HashSet<>();

    public Product(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baseProduct=" + baseProduct +
                ", productComponents=" + productComponents +
                ", productSales=" + productSales +
                '}';
    }

    public Set<Product> getDerivedProducts() {
        return Collections.unmodifiableSet(derivedProducts);
    }

    public void setBaseProduct(Product baseProduct) {
        baseProduct.derivedProducts.add(this);
        this.baseProduct = baseProduct;
    }

    public Set<ProductComponent> getProductComponents() {
        return Collections.unmodifiableSet(productComponents);
    }

    /**
     * @param requiredAmount component amount required to create the product
     */
    public void addProductComponent(Component component, BigDecimal requiredAmount) {
        ProductComponent productComponent = buildProductComponent(component, requiredAmount);
        component.getProductComponents().add(productComponent);
        this.productComponents.add(productComponent);
    }

    private ProductComponent buildProductComponent(Component component, BigDecimal requiredAmount) {
        var productComponentId = new ProductComponentId(this, component);
        return new ProductComponent(productComponentId, requiredAmount);
    }
}
