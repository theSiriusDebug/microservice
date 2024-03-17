package microservice.clientservice.domain;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record ClientRegistrationRequest(
        String username,
        String firstname,
        String surname
) {
}
