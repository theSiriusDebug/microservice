package microservice.product.domain.dto;

import java.math.BigDecimal;

public record ProductRegistrationDto(
        String title,
        String description,
        BigDecimal price) {
}
