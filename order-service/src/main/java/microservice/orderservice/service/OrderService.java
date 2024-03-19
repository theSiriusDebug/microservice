package microservice.orderservice.service;

import microservice.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface OrderService {
    @Transactional
    String placeOrder(OrderRequest request);
}
