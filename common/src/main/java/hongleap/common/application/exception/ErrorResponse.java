package hongleap.common.application.exception;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ErrorResponse(
        String staus,
        Integer code,
        String message,
        ZonedDateTime timestamp
) {
}
