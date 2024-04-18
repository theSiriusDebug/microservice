package microservice.historyservice;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ContainerPostProcessor;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.micrometer.KafkaListenerObservation;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public KafkaListenerObservation.DefaultKafkaListenerObservationConvention kafkaListenerObservation(){
        return new KafkaListenerObservation.DefaultKafkaListenerObservationConvention();
    }
    @Bean
    public ContainerPostProcessor<String, OrderPlacedEvent, AbstractMessageListenerContainer<String, OrderPlacedEvent>> customContainerPostProcessor() {
        return container -> {
            container.getContainerProperties()
                    .setObservationEnabled(true);
            container.getContainerProperties()
                    .setObservationConvention(kafkaListenerObservation());
        };
    }
//
//    @Bean
//    public PrometheusConfig prometheusConfig(){
//        return new PrometheusConfig();
//    }
//    @Bean
//    public PrometheusMeterRegistry prometheusMeterRegistry(){
//        return new PrometheusMeterRegistry(PrometheusConfig);
//    }
}