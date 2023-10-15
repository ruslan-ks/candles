package r.kostiuk.candles.type.validation;

import org.mockito.Mock;
import r.kostiuk.candles.messages.MessageProvider;

public class ComponentTypeControllerValidatorTest extends ComponentTypeValidatorTestBase {
    @Mock
    private MessageProvider messageProvider;
    @Override
    protected ComponentTypeValidator getValidatorUnderTest() {
        return new ComponentTypeControllerValidator(messageProvider);
    }
}
