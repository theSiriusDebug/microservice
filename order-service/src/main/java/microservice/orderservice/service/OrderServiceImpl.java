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
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    private final OrderRepository repository;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
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

        if (Arrays.stream(
                Objects.requireNonNull(
                        inventoryResponsesArray)).allMatch(InventoryResponse::isInStock)) {
            sendToKafka(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }
    }

    private void sendToKafka(Order order){
        Objects.requireNonNull(kafkaTemplate.executeInTransaction(t ->
                t.send("order_event", new OrderPlacedEvent(order.getOrderNumber())))).whenComplete((result, exception) -> {
            if (exception == null) {
                repository.save(order);
            } else {
                System.err.println("Error sending order event to Kafka: " + exception.getMessage());
            }
        });
    }


    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.price());
        orderLineItems.setQuantity(orderLineItemsDto.quantity());
        orderLineItems.setSkuCode(orderLineItemsDto.skuCode());
        return orderLineItems;
    }
}
