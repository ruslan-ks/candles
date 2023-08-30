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
    private Integer id;

    @NaturalId
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_id")
    private Product baseProduct;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "baseProduct")
    private Set<Product> derivedProducts = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<ProductComponent> components = new HashSet<>();

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
                ", components=" + components +
                '}';
    }

    public Collection<Product> getDerivedProducts() {
        return Collections.unmodifiableSet(derivedProducts);
    }

    public void setBaseProduct(Product baseProduct) {
        baseProduct.derivedProducts.add(this);
        this.baseProduct = baseProduct;
    }

    public Collection<ProductComponent> getComponents() {
        return Collections.unmodifiableSet(components);
    }

    private Component extractComponent(ProductComponent productComponent) {
        return productComponent.getId().getComponent();
    }

    /**
     * @param requiredAmount component amount required to create the product
     */
    public void addComponent(Component component, BigDecimal requiredAmount) {
        ProductComponent productComponent = buildProductComponent(component, requiredAmount);
        this.components.add(productComponent);
    }

    private ProductComponent buildProductComponent(Component component, BigDecimal requiredAmount) {
        var productComponentId = new ProductComponentId(this, component);
        return new ProductComponent(productComponentId, requiredAmount);
    }
}
