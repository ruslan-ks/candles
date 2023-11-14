package r.kostiuk.candles.component;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import r.kostiuk.candles.test.util.DBContainerExtension;
import r.kostiuk.candles.test.util.TestEntityFactory;
import r.kostiuk.candles.test.util.TestEntityFactoryImpl;

@ExtendWith(DBContainerExtension.class)
@DataJpaTest
abstract class AbstractComponentSpecificationTest {
    @Autowired
    protected ComponentRepository componentRepository;
    protected TestEntityFactory entityFactory = new TestEntityFactoryImpl();
}