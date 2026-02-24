package hongleap.pipeline_service.client;

import hongleap.pipeline_service.client.dto.UserResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import java.util.List;

@HttpExchange
public interface JsonPlaceholderClient {

    //https://jsonplaceholder.typicode.com/users
    @GetExchange("/users")
    List<UserResponse> getUser();

}
