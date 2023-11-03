package r.kostiuk.candles.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import r.kostiuk.candles.dto.FieldValidationError;
import r.kostiuk.candles.dto.UIMessage;

import java.util.List;

@Getter
@ToString
public class CustomValidationException extends RuntimeException {
    private final List<FieldValidationError> errors;

    public CustomValidationException(Errors errors) {
        this.errors = errors
                .getFieldErrors()
                .stream()
                .map(this::toFieldValidationError)
                .toList();
    }

    private FieldValidationError toFieldValidationError(FieldError error) {
        var message = new UIMessage(error.getCode(), error.getDefaultMessage());
        return new FieldValidationError(error.getField(), error.getRejectedValue(), message);
    }
}
