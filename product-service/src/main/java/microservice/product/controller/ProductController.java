package microservice.product.controller;

import microservice.product.domain.dto.ProductDto;
import microservice.product.domain.dto.ProductRegistrationDto;
import microservice.product.service.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public record ProductController(ProductServiceImpl service) {
    @PostMapping("/create")
    public void createProduct(@RequestBody ProductRegistrationDto registrationDto) {
        service.createProduct(registrationDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> allProducts() {
        return ResponseEntity.ok(service.allProducts());
    }
}
