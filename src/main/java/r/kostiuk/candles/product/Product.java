package r.kostiuk.candles.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.product.component.ProductComponent;
import r.kostiuk.candles.product.sale.ProductSale;
import r.kostiuk.candles.product.component.ProductComponentId;

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

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<ProductComponent> productComponents = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "included_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "included_product_id")
    )
    private Set<Product> includedProducts = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "includedProducts")
    private Set<Product> productsIncludingThis = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<ProductSale> productSales = new HashSet<>();

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
                ", quantityInStock=" + quantityInStock +
                ", productComponents=" + productComponents +
                ", includedProducts=" + includedProducts +
                ", productSales=" + productSales +
                '}';
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

    public Set<Product> getIncludedProducts() {
        return Collections.unmodifiableSet(includedProducts);
    }

    public void addIncludedProduct(Product product) {
        includedProducts.add(product);
        product.productsIncludingThis.add(this);
    }

    public Set<Product> getProductsIncludingThis() {
        return Collections.unmodifiableSet(productsIncludingThis);
    }
}
