package r.kostiuk.candles.dto;

public record FieldValidationError(String field, Object rejectedValue, UIMessage uiMessage) {
}
