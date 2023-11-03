package r.kostiuk.candles.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import r.kostiuk.candles.dto.ApiError;
import r.kostiuk.candles.dto.FieldValidationError;
import r.kostiuk.candles.dto.UIMessage;
import r.kostiuk.candles.exception.CustomValidationException;
import r.kostiuk.candles.messages.MessageProvider;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ValidationExceptionHandler {
    private static final String VALIDATION_FAILED_MESSAGE_CODE = "validationFailed";

    private final MessageProvider messageProvider;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotFound(MethodArgumentNotValidException e) {
        List<FieldValidationError> fieldValidationErrors = mapFieldErrors(e.getBindingResult().getFieldErrors());
        return buildValidationApiError(fieldValidationErrors);
    }

    private List<FieldValidationError> mapFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(this::toFieldValidationError)
                .toList();
    }

    private FieldValidationError toFieldValidationError(FieldError error) {
        var uiMessage = new UIMessage(error.getCode(), error.getDefaultMessage());
        return new FieldValidationError(error.getField(), error.getRejectedValue(), uiMessage);
    }

    private ApiError buildValidationApiError(List<FieldValidationError> validationErrors) {
        UIMessage uiMessage = messageProvider.getDefaultUIMessage(VALIDATION_FAILED_MESSAGE_CODE);
        return ApiError.badRequest(uiMessage, Map.of("invalidFields", validationErrors));
    }

    @ExceptionHandler(CustomValidationException.class)
    public ApiError handleCustomValidationException(CustomValidationException e) {
        return buildValidationApiError(e.getErrors());
    }
}
