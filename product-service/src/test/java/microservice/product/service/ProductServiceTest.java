//package microservice.product.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import microservice.product.domain.dto.ProductRegistrationDto;
//import microservice.product.repository.ProductRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.math.BigDecimal;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceTest {
//
//    @Container
//    static MongoDBContainer container = new MongoDBContainer("mongo:6.0.2");
//
//    private final ProductRepository repository;
//
//    private final MockMvc mvc;
//
//    private final ObjectMapper mapper;
//
//    static {
//        container.start();
//    }
//
//    @Autowired
//    ProductServiceTest(ProductRepository repository, MockMvc mvc, ObjectMapper mapper) {
//        this.repository = repository;
//        this.mvc = mvc;
//        this.mapper = mapper;
//    }
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/products/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(getProduct())))
//                .andExpect(status().isOk());
//        Assertions.assertEquals(1, repository.findAll().size());
//    }
//
//    private ProductRegistrationDto getProduct() {
//        return ProductRegistrationDto.builder()
//                .title("title")
//                .description("description")
//                .price(BigDecimal.valueOf(12321))
//                .build();
//    }
//}
