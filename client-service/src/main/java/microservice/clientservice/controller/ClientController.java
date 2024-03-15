package microservice.clientservice.controller;

import microservice.clientservice.domain.Client;
import microservice.clientservice.domain.ClientRegistrationRequest;
import microservice.clientservice.service.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public record ClientController(ClientServiceImpl service) {

    @GetMapping
    public String getString() {
        return "Hello!";
    }

    @PostMapping
    public Client createClient(@RequestBody ClientRegistrationRequest request) {
        return service.createClient(request);
    }
}
