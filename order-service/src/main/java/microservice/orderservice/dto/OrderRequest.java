package microservice.orderservice.dto;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemsDto> orderLineItemsDtoList) {
}
