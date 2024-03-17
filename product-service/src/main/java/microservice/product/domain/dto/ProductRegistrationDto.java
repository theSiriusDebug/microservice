package microservice.product.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRegistrationDto(
        String title,
        String description,
        BigDecimal price) {
}
