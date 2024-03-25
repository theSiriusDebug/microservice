package microservice.orderservice.service;

import lombok.RequiredArgsConstructor;
import microservice.orderservice.dto.InventoryResponse;
import microservice.orderservice.dto.OrderLineItemsDto;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.event.OrderPlacedEvent;
import microservice.orderservice.model.Order;
import microservice.orderservice.model.OrderLineItems;
import microservice.orderservice.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    @Transactional
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.orderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponsesArray = webClient.build().get()
                .uri("http://localhost:8765/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if (Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock)) {
            kafkaTemplate.send("topic1", new OrderPlacedEvent(order.getOrderNumber()));
            repository.save(order);
            return "Order placed successfully!";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.price());
        orderLineItems.setQuantity(orderLineItemsDto.quantity());
        orderLineItems.setSkuCode(orderLineItemsDto.skuCode());
        return orderLineItems;
    }
}
