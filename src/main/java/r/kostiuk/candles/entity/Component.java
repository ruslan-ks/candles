package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "components")
public class Component implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    @Column(name = "amount_in_stock", columnDefinition = "numeric(12, 3)")
    private BigDecimal amountInStock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id")
    private ComponentType type;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.component")
    private Set<ProductComponent> productComponents = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "component")
    private Set<ComponentReceipt> receipts = new HashSet<>();

    public Component(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Component component)) {
            return false;
        }
        return Objects.equals(name, component.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountInStock=" + amountInStock +
                ", type=" + type +
                '}';
    }

    public Set<ComponentReceipt> getComponentReceipts() {
        return Collections.unmodifiableSet(receipts);
    }

    public void addReceipt(ComponentReceipt receipt) {
        receipt.setComponent(this);
        receipts.add(receipt);
    }
}
