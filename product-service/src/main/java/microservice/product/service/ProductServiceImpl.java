package microservice.product.service;

import lombok.extern.slf4j.Slf4j;
import microservice.product.domain.dto.ProductDto;
import microservice.product.domain.dto.ProductRegistrationDto;
import microservice.product.domain.mapper.ProductDtoMapper;
import microservice.product.domain.model.Product;
import microservice.product.exception.ValidationException;
import microservice.product.infrastructure.validator.ProductValidator;
import microservice.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;

@Slf4j
@Service
public record ProductServiceImpl(
        ProductRepository repository, ProductValidator validator) implements ProductService {
    @Override
    public void createProduct(ProductRegistrationDto registrationDto) {
        Product product = Product.builder()
                .title(registrationDto.title())
                .description(registrationDto.description())
                .price(registrationDto.price())
                .build();
        Errors errors = new BeanPropertyBindingResult(product, "product");
        ValidationUtils.invokeValidator(validator, product, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors());
        } else {
            repository.save(product);
            log.info("Product '{}' (ID: {}) created", product.getTitle(), product.getId());
        }
    }

    @Override
    public List<ProductDto> allProducts() {
        log.trace("all products");
        return repository.findAll().stream()
                .map(ProductDtoMapper::mapToProductDto).toList();
    }
}
