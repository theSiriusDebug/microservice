package microservice.orderservice.event;

public record OrderPlacedEvent(
        String orderNumber) {
}
