package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@ToString
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
    private BigDecimal amountInStock = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id")
    private ComponentType type;

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
}
