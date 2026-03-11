package hongleap.common.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;

import java.util.UUID;

public record AccountId(UUID value) {
    @NonNull
    @Override
    public String toString() {
        return value.toString();
    }

    @JsonCreator
    public static AccountId from(String id) {
        return new AccountId(UUID.fromString(id));
    }

}
