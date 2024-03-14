package microservice.product.service;

import lombok.extern.slf4j.Slf4j;
import microservice.product.domain.dto.ProductRegistrationDto;
import microservice.product.domain.model.Product;
import microservice.product.exception.ValidationException;
import microservice.product.infrastructure.validator.ProductValidator;
import microservice.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductValidator validator;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    @Transactional
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
}
