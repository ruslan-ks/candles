package r.kostiuk.candles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import r.kostiuk.candles.test.util.PostgresContainerExtension;

@ExtendWith(PostgresContainerExtension.class)
@SpringBootTest
class CandlesApplicationTests {

    @Test
    void contextLoads() {
    }

}
