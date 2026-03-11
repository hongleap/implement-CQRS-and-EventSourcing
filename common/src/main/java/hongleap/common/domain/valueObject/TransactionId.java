package hongleap.common.domain.valueObject;

import lombok.NonNull;

import java.util.UUID;

public record TransactionId(
        UUID value
) {
    @NonNull
    @Override
    public String toString() {
        return value.toString();
    }
}