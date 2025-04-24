package nakup.inventory.service;

import jakarta.transaction.Transactional;
import nakup.inventory.dto.ChangeQuantityRequest;
import nakup.inventory.dto.InventoryCreateRequest;
import nakup.inventory.dto.InventoryResponse;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.InventoryRepository;
import nakup.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Transactional
    public void save(InventoryCreateRequest request,  Warehouse warehouse) {
        Inventory inventory = new Inventory();
        List<Inventory> warehouseInventory = warehouse.getInventory();

        inventory.setQuantity(request.getQuantity());
        inventory.setWarehouse(warehouse);
        warehouseInventory.add(inventory);
        inventory.setProductId(request.getProductId());
        inventoryRepository.save(inventory);
    }

    @Transactional
    public InventoryResponse changeQuantity(ChangeQuantityRequest request) {
        //System.out.println(request.getProductId() + ", " + request.getChangeQuantity() + ", by: " + request.getChangeQuantityBy());
        Inventory inventory = inventoryRepository.findByProductId(request.getProductId());
        if (inventory == null) {
            throw new RuntimeException("Product with id- " + request.getProductId() + " not found");
        }

        if (request.getChangeQuantityBy() != null) {
            inventory.setQuantity(inventory.getQuantity() + request.getChangeQuantityBy());
            inventory.setUpdatedAt(LocalDateTime.now());
            return new InventoryResponse(inventory);
        }
        else if (request.getChangeQuantity() != null) {
            inventory.setQuantity(request.getChangeQuantity());
            inventory.setUpdatedAt(LocalDateTime.now());
            return new InventoryResponse(inventory);
        }
        throw new RuntimeException("Both changeQuantity and changeQuantityBy are null");
    }

//    public void addStock(ChangeQuantityRequest request) {
//        Inventory inventory = inventoryRepository.findByProductId(request.getProductId());
//        if (inventory == null) {
//
//        }
//    }

    //public void reserve
}
