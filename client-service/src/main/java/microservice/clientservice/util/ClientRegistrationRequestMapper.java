package microservice.clientservice.util;

import microservice.clientservice.domain.Client;
import microservice.clientservice.domain.ClientRegistrationRequest;

public class ClientRegistrationRequestMapper {
    public static Client mapToClient(ClientRegistrationRequest request) {
        return Client.builder()
                .username(request.username())
                .firstname(request.firstname())
                .surname(request.surname())
                .build();
    }
}
