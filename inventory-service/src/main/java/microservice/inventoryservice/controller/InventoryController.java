package microservice.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import microservice.inventoryservice.dto.InventoryResponse;
import microservice.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return service.isInStock(skuCode);
    }
}
