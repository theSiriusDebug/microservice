package microservice.orderservice.configuration;

import org.springframework.kafka.support.serializer.JsonSerializer;
import microservice.orderservice.event.OrderPlacedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservation;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public KafkaTemplateObservation.DefaultKafkaTemplateObservationConvention kafkaTemplateObservation(){
        return new KafkaTemplateObservation.DefaultKafkaTemplateObservationConvention();
    }

    @Bean
    public ProducerFactory<String, OrderPlacedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.
                VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate(){
        KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setObservationEnabled(true);
        kafkaTemplate.setObservationConvention(kafkaTemplateObservation());
        return kafkaTemplate;
    }
}
