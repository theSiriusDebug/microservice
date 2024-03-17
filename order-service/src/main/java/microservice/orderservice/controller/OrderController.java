package microservice.orderservice.controller;

import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.model.Order;
import microservice.orderservice.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public record OrderController(OrderServiceImpl service) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}
