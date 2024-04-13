package microservice.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.model.Order;
import microservice.orderservice.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(service.placeOrder(request), HttpStatus.CREATED);
    }
}
