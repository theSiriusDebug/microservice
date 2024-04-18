package microservice.orderservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponse(
        String skuCode,
        boolean isInStock) {
}