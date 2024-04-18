package microservice.historyservice;

public record OrderPlacedEvent(
        String orderNumber) {
}
