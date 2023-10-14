package r.kostiuk.candles.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import r.kostiuk.candles.exception.CustomValidationException;

public abstract class CustomValidator<T> implements Validator {

    public void validate(T target) {
        Errors errors = new BeanPropertyBindingResult(target, target.getClass().getSimpleName());
        validate(target, errors);
        if (errors.hasErrors()) {
            throw createValidationException(errors);
        }
    }

    protected abstract CustomValidationException createValidationException(Errors errors);
}
