package microservice.inventoryservice;

import microservice.inventoryservice.event.OrderPlacedEvent;
import microservice.inventoryservice.model.Inventory;
import microservice.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository repository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("test");
            inventory.setQuantity(100);

            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("test1");
            inventory1.setQuantity(0);

            repository.save(inventory);
            repository.save(inventory1);
        };
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(OrderPlacedEvent event) {
        System.out.println("Hello order - " + event);
    }
}
