package microservice.product.service;

import microservice.product.domain.dto.ProductDto;
import microservice.product.domain.dto.ProductRegistrationDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRegistrationDto registrationDto);
    List<ProductDto> allProducts();
}
