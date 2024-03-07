//package microservice.product.service;
//
//import microservice.product.domain.dto.ProductRegistrationDto;
//import microservice.product.domain.model.Product;
//import microservice.product.exception.ValidationException;
//import microservice.product.infrastructure.validator.ProductValidator;
//import microservice.product.repository.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//import java.math.BigDecimal;
//
//@ExtendWith(MockitoExtension.class)
//class ProductServiceTest {
//
//    @Mock
//    private ProductRepository repository;
//
//    @Mock
//    private ProductValidator validator;
//
//    @InjectMocks
//    private ProductService service;
//
//    private ProductRegistrationDto productDto;
//
//    @BeforeEach
//    void setUp() {
//        productDto = new ProductRegistrationDto(
//                "Product Title",
//                "Product Description",
//                BigDecimal.valueOf(100.0)
//        );
//    }
//
//    @Test
//    void createProduct_ValidProduct_ShouldSaveProduct() throws ValidationException {
//        // given
//        Errors errors = new BeanPropertyBindingResult(productDto, "productDto");
//        when(validator.supports(ProductRegistrationDto.class)).thenReturn(true);
//        doNothing().when(validator).validate(productDto, errors);
//
//        // when
//        service.createProduct(productDto);
//
//        // then
//        verify(repository, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    void createProduct_InvalidProduct_ShouldThrowValidationException() throws ValidationException {
//        // given
//        Errors errors = new BeanPropertyBindingResult(productDto, "productDto");
//        when(validator.supports(Product.class)).thenReturn(true);
//        doThrow(new ValidationException(errors.getAllErrors())).when(validator).validate(productDto, errors);
//        doThrow(new ValidationException(errors.getAllErrors())).when(repository).save(any(Product.class));
//
//        // when and then
//        assertThrows(ValidationException.class, () -> service.createProduct(productDto));
//        verify(repository, times(1)).save(any(Product.class));
//    }
//}