package microservice.product.controller;

import microservice.product.domain.dto.ProductRegistrationDto;
import microservice.product.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public record ProductController(ProductServiceImpl service) {

    @PostMapping
    public void createProduct(@RequestBody ProductRegistrationDto registrationDto) {
        service.createProduct(registrationDto);
    }
}
