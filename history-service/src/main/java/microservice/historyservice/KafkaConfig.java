package microservice.historyservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.micrometer.KafkaListenerObservation;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public KafkaListenerObservation.DefaultKafkaListenerObservationConvention kafkaListenerObservationConvention(){
        return new KafkaListenerObservation.DefaultKafkaListenerObservationConvention();
    }
}