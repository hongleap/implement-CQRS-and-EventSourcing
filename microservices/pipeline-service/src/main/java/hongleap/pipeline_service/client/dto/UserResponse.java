package hongleap.pipeline_service.client.dto;

public record UserResponse(
        Integer id,
        String username,
        String email,
        String phoneNumber
) {
}
