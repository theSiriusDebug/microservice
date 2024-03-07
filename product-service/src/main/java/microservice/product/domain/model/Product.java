package microservice.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> tags;
    private List<String> categories;
    private List<String> images;
    private List<String> videos;
    private int count;
}
