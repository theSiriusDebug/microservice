package microservice.inventoryservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponse(
        String skuCode,
        boolean isInStock) {
}
