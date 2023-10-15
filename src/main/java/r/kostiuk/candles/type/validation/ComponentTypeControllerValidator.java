package r.kostiuk.candles.type.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import r.kostiuk.candles.exception.ControllerValidationException;
import r.kostiuk.candles.exception.CustomValidationException;

@Component
public class ComponentTypeControllerValidator extends ComponentTypeValidator {
    @Override
    protected CustomValidationException createValidationException(Errors errors) {
        return new ControllerValidationException(errors);
    }
}
