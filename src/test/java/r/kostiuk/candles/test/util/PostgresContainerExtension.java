package r.kostiuk.candles.test.util;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerExtension implements BeforeAllCallback {
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        postgres.start();
        setDatasourceProperties();
    }

    private static void setDatasourceProperties() {
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }
}
