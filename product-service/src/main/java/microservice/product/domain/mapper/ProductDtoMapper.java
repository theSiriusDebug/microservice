package microservice.product.domain.mapper;

import microservice.product.domain.dto.ProductDto;
import microservice.product.domain.model.Product;

public class ProductDtoMapper {
    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getTitle(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
