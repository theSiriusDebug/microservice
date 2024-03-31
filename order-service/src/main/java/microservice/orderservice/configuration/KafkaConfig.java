package microservice.orderservice.configuration;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import microservice.orderservice.event.OrderPlacedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, OrderPlacedEvent> producerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "order-service");

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate(
            ProducerFactory<String, OrderPlacedEvent> producerFactory) {
        KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate = new KafkaTemplate<>(
                producerFactory, Collections.singletonMap(
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class));
        kafkaTemplate.setObservationEnabled(true);
        kafkaTemplate.setObservationConvention(kafkaTemplateObservation());
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplateObservation.DefaultKafkaTemplateObservationConvention kafkaTemplateObservation(){
        return new KafkaTemplateObservation.DefaultKafkaTemplateObservationConvention();
    }
}

