package hongleap.account_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class UnsecuredController {

    @Value("${service.name}")
    String name;
    @Value("${secret.weak-password}")
    String weakPassword;
    @Value("${secret.strong-password}")
    String strongPassword;

    @GetMapping
    public Map<String, String> unsecureEndpoint() {
        return Map.of(
                "serviceName", name,
                "weakPassword", weakPassword,
                "strongPassword", strongPassword
        );
    }

}
