package microservice.historyservice;

import io.micrometer.core.instrument.Counter;
import io.micrometer.observation.Observation;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Objects;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class HistoryServiceApplication {
    private final PrometheusMeterRegistry registry;

    public static void main(String[] args) {
        SpringApplication.run(HistoryServiceApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "order_event", containerPostProcessor = "customContainerPostProcessor")
    public void handleNotification(OrderPlacedEvent event) {
        Counter counter = registry.counter("on-message");
        counter.increment();
        registry.scrape();
        log.info("Got message <{}>", event);
    }
}
