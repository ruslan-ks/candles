package r.kostiuk.candles.receipt;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import r.kostiuk.candles.component.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
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

    @Column(name = "amount_received", columnDefinition = "numeric(12, 3)")
    private BigDecimal amountReceived;

    @Column(name = "total_price", columnDefinition = "numeric(12, 2)")
    private BigDecimal totalPrice;

    @Column(name = "received_on")
    private LocalDate receivedOn;

    private String comment;

    public ComponentReceipt(BigDecimal amountReceived, BigDecimal totalPrice, LocalDate receivedOn) {
        this.amountReceived = amountReceived;
        this.totalPrice = totalPrice;
        this.receivedOn = receivedOn;
    }

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
                ", amountReceived=" + amountReceived +
                ", totalPrice=" + totalPrice +
                ", receivedOn=" + receivedOn +
                ", comment='" + comment + "'" +
                '}';
    }
}
