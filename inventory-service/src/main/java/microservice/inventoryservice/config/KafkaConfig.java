//package microservice.inventoryservice.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.ByteArraySerializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//
//// Class
//public class KafkaConfig {
//    @Bean
//    public KafkaTemplate<String, String> stringTemplate(ProducerFactory<String, String> pf) {
//        return new KafkaTemplate<>(pf);
//    }
//
//    @Bean
//    public KafkaTemplate<String, byte[]> bytesTemplate(ProducerFactory<String, byte[]> pf) {
//        return new KafkaTemplate<>(pf,
//                Collections.singletonMap(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
//    }
//}
