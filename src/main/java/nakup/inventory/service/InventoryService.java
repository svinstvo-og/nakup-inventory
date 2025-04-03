package nakup.inventory.service;

import jakarta.transaction.Transactional;
import nakup.inventory.dto.InventoryCreateRequest;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.InventoryRepository;
import nakup.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Warehouse> warehouses = new ArrayList<>();
        List<Inventory> inventories = warehouse.getInventory();
        inventories.add(inventory);
        warehouses.add(warehouse);

        inventory.setQuantity(request.getQuantity());
        inventory.setWarehouse(warehouses);
        inventory.setProductId(request.getProductId());
        inventoryRepository.save(inventory);
        warehouse.setInventory(inventories);
    }
}
