package microservice.historyservice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservation;

import java.util.Objects;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class HistoryServiceApplication {
    private final ObservationRegistry registry;
    private final Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(HistoryServiceApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "order_event")
    public void handleNotification(OrderPlacedEvent event) {
        Observation.createNotStarted("on-message", this.registry).observe(() -> {
            log.info("Got message <{}>", event);
            log.info("TraceId- {}, Received Notification for Order - {}", Objects.requireNonNull(this.tracer.currentSpan()).context().traceId(), event.orderNumber());
        });
    }
}
