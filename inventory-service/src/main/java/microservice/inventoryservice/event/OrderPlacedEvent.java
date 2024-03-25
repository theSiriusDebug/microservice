package microservice.inventoryservice.event;

public record OrderPlacedEvent(
        String orderNumber) {
}
