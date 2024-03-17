package microservice.orderservice.service;

import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface OrderService {
    @Transactional
    void placeOrder(OrderRequest request);
}
