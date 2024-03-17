package microservice.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderLineItemsDto(
        UUID id,
        String skuCode,
        BigDecimal price,
        Integer quantity) {
}
