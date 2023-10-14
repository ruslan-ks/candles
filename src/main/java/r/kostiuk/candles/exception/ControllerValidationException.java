package r.kostiuk.candles.exception;

import org.springframework.validation.Errors;

public class ControllerValidationException extends CustomValidationException {
    public ControllerValidationException(Errors errors) {
        super(errors);
    }
}
