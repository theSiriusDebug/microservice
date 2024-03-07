package microservice.product.domain.dto;

import java.math.BigDecimal;

public record ProductDto(
        String title,
        String description,
        BigDecimal price
) {
}
