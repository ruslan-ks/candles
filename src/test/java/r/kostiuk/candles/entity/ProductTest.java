package r.kostiuk.candles.entity;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import r.kostiuk.candles.product.Product;
import r.kostiuk.candles.util.TestData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class ProductTest {

    @Nested
    class IncludedProductsTest {

        @Test
        void addMultipleIncludedProducts() {
            var compoundProduct = createProduct("Compound Product", BigDecimal.ONE);
            List<Product> simpleProducts = TestData.generateProducts(5).toList();

            simpleProducts.forEach(compoundProduct::addIncludedProduct);

            Set<Product> includedProducts = compoundProduct.getIncludedProducts();
            assertThat(includedProducts).containsExactlyInAnyOrderElementsOf(simpleProducts);
        }

        @Test
        void addIncludedProductUpdatesIncludedProduct() {
            var simpleProduct = createProduct("Simple Product", BigDecimal.ONE);
            var compoundProduct = createProduct("Compound Product", BigDecimal.TEN);

            compoundProduct.addIncludedProduct(simpleProduct);

            Set<Product> productsIncludingSimpleProduct = simpleProduct.getProductsIncludingThis();
            assertThat(productsIncludingSimpleProduct).containsExactly(compoundProduct);
        }

        @Test
        void includeProductIntoMultipleOtherProducts() {
            var simpleProduct = createProduct("Compound Product", BigDecimal.ONE);
            List<Product> compoundProducts = TestData.generateProducts(5).toList();

            compoundProducts.forEach(compoundProduct -> compoundProduct.addIncludedProduct(simpleProduct));

            Set<Product> productsIncludingSimpleProduct = simpleProduct.getProductsIncludingThis();
            assertThat(productsIncludingSimpleProduct).containsExactlyInAnyOrderElementsOf(compoundProducts);
        }
    }

    private Product createProduct(String name, BigDecimal price) {
        return new Product(name, price);
    }
}