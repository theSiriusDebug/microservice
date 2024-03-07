package microservice.product.infrastructure.validator;

import microservice.product.domain.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "Description is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required", "Price is required");

        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("price", "field.invalid", "Price must be greater than zero");
        }
    }
}