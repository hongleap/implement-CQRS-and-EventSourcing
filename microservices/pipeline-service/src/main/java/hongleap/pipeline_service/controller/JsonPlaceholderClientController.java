package hongleap.pipeline_service.controller;

import hongleap.pipeline_service.client.JsonPlaceholderClient;
import hongleap.pipeline_service.client.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/client/jph")
@RestController
@RequiredArgsConstructor
public class JsonPlaceholderClientController {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    @GetMapping("/users")
    List<UserResponse> getClient(){
        return jsonPlaceholderClient.getUser();
    }

}
