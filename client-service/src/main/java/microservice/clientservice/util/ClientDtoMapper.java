package microservice.clientservice.util;

import microservice.clientservice.domain.Client;
import microservice.clientservice.domain.ClientDto;

public class ClientDtoMapper {

    public static ClientDto mapToClientDto(Client client) {
        return ClientDto.builder()
                .username(client.getUsername())
                .firstname(client.getFirstname())
                .surname(client.getSurname())
                .build();
    }
}
