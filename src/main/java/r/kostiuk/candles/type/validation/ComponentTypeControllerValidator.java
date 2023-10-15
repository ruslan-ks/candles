package r.kostiuk.candles.type.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import r.kostiuk.candles.exception.ControllerValidationException;
import r.kostiuk.candles.exception.CustomValidationException;
import r.kostiuk.candles.messages.MessageProvider;

@Component
public class ComponentTypeControllerValidator extends ComponentTypeValidator {
    public ComponentTypeControllerValidator(MessageProvider messageProvider) {
        super(messageProvider);
    }

    @Override
    protected CustomValidationException createValidationException(Errors errors) {
        return new ControllerValidationException(errors);
    }
}
