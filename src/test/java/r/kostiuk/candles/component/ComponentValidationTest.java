package r.kostiuk.candles.component;

import r.kostiuk.candles.component.vaildation.ValidComponent;

public class ComponentValidationTest extends ComponentValidationTestBase {
    @Override
    protected ValidComponent getImplementationUnderTest() {
        return new Component();
    }
}
