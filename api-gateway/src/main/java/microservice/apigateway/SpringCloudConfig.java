//package microservice.apigateway;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringCloudConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/clients/**")
//                        .uri("lb://CLIENT-SERVICE")
//                        .id("employeeModule"))
//
//                .route(r -> r.path("/consumer/**")
//                        .uri("lb://SECOND-SERVICE")
//                        .id("consumerModule"))
//                .build();
//    }
//
//}
