package hongleap.common.domain.valueObject;


import com.fasterxml.jackson.annotation.JsonCreator;import com.fasterxml.jackson.annotation.JsonValue;import lombok.NonNull;

import java.util.UUID;

public record CustomerId(UUID value) {

    @NonNull
    @Override
    public String toString() {
        return value.toString();
    }

    @JsonCreator
    public static CustomerId from(String value) {
        return new CustomerId(UUID.fromString(value));
    }

    @JsonValue
    public String toJson() {
        return value.toString();
    }

}