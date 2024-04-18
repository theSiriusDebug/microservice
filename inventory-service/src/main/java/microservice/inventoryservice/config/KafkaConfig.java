//package microservice.inventoryservice.config;
//
//import microservice.inventoryservice.dto.InventoryResponse;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConfig {
//    @Value("spring.kafka.producer.bootstrap-servers")
//    private String bootstrapAddress;
//
//    @Bean
//    public ProducerFactory<String, List<InventoryResponse>> producerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(
//                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapAddress);
//        props.put(
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
//        props.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, List<InventoryResponse>> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//}
