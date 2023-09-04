package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "component_receipts")
public class ComponentReceipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Component component;

    @Column(columnDefinition = "numeric(12, 2)")
    private BigDecimal amount;

    @Column(name = "price_per_piece", columnDefinition = "numeric(12, 2)")
    private BigDecimal pricePerPiece;

    @Column(name = "received_on")
    private LocalDate receivedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentReceipt receipt)) {
            return false;
        }
        return id != null && Objects.equals(id, receipt.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ComponentReceipt{" +
                "id=" + id +
                ", component.id=" + component.getId() +
                ", amount=" + amount +
                ", pricePerPiece=" + pricePerPiece +
                ", receivedOn=" + receivedOn +
                '}';
    }
}
