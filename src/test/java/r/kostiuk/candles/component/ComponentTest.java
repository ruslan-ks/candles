package r.kostiuk.candles.component;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import r.kostiuk.candles.receipt.ComponentReceipt;
import r.kostiuk.candles.util.TestData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class ComponentTest {

    @Nested
    class ComponentWithReceiptsTest {

        @Test
        void addComponentReceiptUpdatesComponentReceipt() {
            Component component = createTestComponent();
            ComponentReceipt receipt = createTestReceipt();

            component.addReceipt(receipt);

            assertThat(receipt.getComponent()).isSameAs(component);
        }

        @Test
        void addMultipleComponentReceipts() {
            Component component = createTestComponent();
            List<ComponentReceipt> receiptsToBeAdded = TestData.generateComponentReceipts(5).toList();

            receiptsToBeAdded.forEach(component::addReceipt);

            Set<ComponentReceipt> componentReceipts = component.getReceipts();
            assertThat(componentReceipts).containsExactlyInAnyOrderElementsOf(receiptsToBeAdded);
        }
    }

    private Component createTestComponent() {
        return new Component("Wax");
    }

    private ComponentReceipt createTestReceipt() {
        return new ComponentReceipt(BigDecimal.valueOf(3), BigDecimal.valueOf(600), LocalDate.now());
    }
}