package r.kostiuk.candles.component.type.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import r.kostiuk.candles.component.type.ComponentTypeService;
import r.kostiuk.candles.exception.ControllerValidationException;
import r.kostiuk.candles.exception.CustomValidationException;

@Component
public class ComponentTypeControllerValidator extends ComponentTypeValidator {
    public ComponentTypeControllerValidator(ComponentTypeService typeService) {
        super(typeService);
    }

    @Override
    protected CustomValidationException createValidationException(Errors errors) {
        return new ControllerValidationException(errors);
    }
}