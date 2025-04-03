package nakup.inventory.controller;

import nakup.inventory.dto.InventoryCreateRequest;
import nakup.inventory.dto.InventoryResponse;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.InventoryRepository;
import nakup.inventory.service.InventoryService;
import nakup.inventory.service.WarehouseService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public String createInventory(@RequestBody InventoryCreateRequest request) {
        if (inventoryRepository.findByProductId(request.getProductId()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
        Warehouse warehouse = warehouseService.validate(request.getWarehouseId());
        inventoryService.save(request, warehouse);
        return "successssssssssssss";
    }

    @PutMapping
    public void updateWarehouse() {};

    @GetMapping("/all")
    public List<InventoryResponse> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryResponse> response = new ArrayList<>();

        for (Inventory inventory : inventories) {
            response.add(new InventoryResponse(inventory));
        }
        return response;
    }
}
