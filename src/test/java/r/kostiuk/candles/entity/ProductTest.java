package r.kostiuk.candles.entity;

import org.junit.jupiter.api.Test;
import r.kostiuk.candles.util.TestData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test
    void addMultipleIncludedProducts() {
        var compoundProduct = new Product("Compound Product", BigDecimal.ONE);
        List<Product> simpleProducts = TestData.generateProducts(5).toList();

        simpleProducts.forEach(compoundProduct::addIncludedProduct);

        Set<Product> includedProducts = compoundProduct.getIncludedProducts();
        assertThat(includedProducts).containsExactlyInAnyOrderElementsOf(simpleProducts);
    }

    @Test
    void addIncludedProductUpdatesBothSidesOfRelation() {
        var simpleProduct = new Product("Simple Product", BigDecimal.ONE);
        var compoundProduct = new Product("Compound Product", BigDecimal.TEN);

        compoundProduct.addIncludedProduct(simpleProduct);

        Set<Product> productsIncludingSimpleProduct = simpleProduct.getProductsIncludingThis();
        assertThat(productsIncludingSimpleProduct).containsExactly(compoundProduct);
    }

    @Test
    void includeProductIntoMultipleOtherProducts() {
        var simpleProduct = new Product("Compound Product", BigDecimal.ONE);
        List<Product> compoundProducts = TestData.generateProducts(5).toList();

        compoundProducts.forEach(compoundProduct -> compoundProduct.addIncludedProduct(simpleProduct));

        Set<Product> productsIncludingSimpleProduct = simpleProduct.getProductsIncludingThis();
        assertThat(productsIncludingSimpleProduct).containsExactlyInAnyOrderElementsOf(compoundProducts);
    }
}