package hongleap.common.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;

import java.util.UUID;

public record BranchId(
        UUID value
) {
    @NonNull
    @Override
    public String toString() {
        return value.toString();
    }

}