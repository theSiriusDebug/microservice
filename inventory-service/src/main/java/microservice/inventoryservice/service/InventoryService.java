package microservice.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import microservice.inventoryservice.dto.InventoryResponse;
import microservice.inventoryservice.model.Inventory;
import microservice.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    @Transactional
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        log.info("Checking and reserving inventory");
        List<InventoryResponse> responses = new ArrayList<>();

        for (String sku : skuCodes) {
            Inventory inventory = repository.findBySkuCode(sku);
            if (inventory != null) {
                if (inventory.getQuantity() >= 1) {
                    // Reserve the quantity
                    inventory.setQuantity(inventory.getQuantity() - 1);

                    responses.add(InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(true)
                            .build());
                } else {
                    responses.add(InventoryResponse.builder()
                            .skuCode(sku)
                            .isInStock(false)
                            .build());
                }
            } else {
                responses.add(InventoryResponse.builder()
                        .skuCode(sku)
                        .isInStock(false)
                        .build());
            }
        }
        return responses;
    }

}