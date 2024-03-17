package microservice.product.service;

import microservice.product.domain.dto.ProductRegistrationDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    @Transactional
    void createProduct(ProductRegistrationDto registrationDto);
}
