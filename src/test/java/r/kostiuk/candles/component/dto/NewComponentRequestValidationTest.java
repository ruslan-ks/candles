package r.kostiuk.candles.component.dto;

import r.kostiuk.candles.component.ComponentValidationTestBase;
import r.kostiuk.candles.component.vaildation.ValidComponent;

public class NewComponentRequestValidationTest extends ComponentValidationTestBase {
    @Override
    protected ValidComponent getImplementationUnderTest() {
        return new NewComponentRequest();
    }
}
