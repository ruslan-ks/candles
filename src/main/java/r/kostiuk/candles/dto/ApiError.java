package r.kostiuk.candles.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

public record ApiError(int statusCode, UIMessage uiMessage, Instant timestamp, Map<String, ?> details) {
    public static ApiError of(HttpStatus status, UIMessage uiMessage, Map<String, ?> details) {
        return new ApiError(status.value(), uiMessage, Instant.now(), details);
    }

    public static ApiError badRequest(UIMessage uiMessage, Map<String, ?> details) {
        return of(HttpStatus.BAD_REQUEST, uiMessage, details);
    }
}
