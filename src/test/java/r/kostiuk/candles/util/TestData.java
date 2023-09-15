package r.kostiuk.candles.util;

import r.kostiuk.candles.entity.Product;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class TestData {
    public static Stream<Product> generateProducts(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(TestData::buildProduct);
    }

    private static Product buildProduct(long productNumber) {
        var product = new Product("Product" + productNumber, BigDecimal.valueOf(productNumber));
        product.setId(productNumber);
        return product;
    }
}
