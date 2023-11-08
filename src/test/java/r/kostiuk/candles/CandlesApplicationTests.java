package r.kostiuk.candles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import r.kostiuk.candles.test.util.DBContainerExtension;

@ExtendWith(DBContainerExtension.class)
@SpringBootTest
class CandlesApplicationTests {

    @Test
    void contextLoads() {
    }

}
