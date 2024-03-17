package microservice.clientservice.domain;

import lombok.Builder;

@Builder
public record ClientDto(
        String username,
        String firstname,
        String surname
) {
}
