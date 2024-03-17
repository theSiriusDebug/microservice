package microservice.inventoryservice.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "t_inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String skuCode;
    private Integer quantity;
}