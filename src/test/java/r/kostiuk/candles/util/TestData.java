package r.kostiuk.candles.util;

import r.kostiuk.candles.component.receipt.ComponentReceipt;
import r.kostiuk.candles.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class TestData {

    public static Stream<Product> generateProducts(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(TestData::buildProduct);
    }

    private static Product buildProduct(long productId) {
        var product = new Product("Product" + productId, BigDecimal.valueOf(productId));
        product.setId(productId);
        return product;
    }

    public static Stream<ComponentReceipt> generateComponentReceipts(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(TestData::buildComponentReceipt);
    }

    private static ComponentReceipt buildComponentReceipt(long receiptId) {
        var receipt = new ComponentReceipt(BigDecimal.valueOf(receiptId), BigDecimal.valueOf(receiptId * 10),
                LocalDate.now());
        receipt.setId(receiptId);
        return receipt;
    }
}
