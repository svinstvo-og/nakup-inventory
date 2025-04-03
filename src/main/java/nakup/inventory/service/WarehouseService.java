package nakup.inventory.service;

import jakarta.transaction.Transactional;
import nakup.inventory.dto.WarehouseAddRequest;
import nakup.inventory.dto.WarehouseResponse;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.InventoryRepository;
import nakup.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Warehouse validate(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse == null) {
            throw new RuntimeException("Warehouse with id " + warehouseId + " not found");
        }
        System.out.println("warehouse: id-" + warehouse.getId() + " " + warehouse.getName());
        return warehouse;
    }

    public void addWarehouse (WarehouseAddRequest request) {
        Warehouse warehouse = new Warehouse();
        List<Inventory> inventory = new ArrayList<>();

        warehouse.setName(request.getName());
        warehouse.setAddress(request.getAddress());
        warehouse.setInventory(inventory);
        warehouse.setCreated(LocalDateTime.now());
        warehouseRepository.save(warehouse);
    }

    public List<WarehouseResponse> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<WarehouseResponse> response = new ArrayList<>();

        for (Warehouse warehouse : warehouses) {
            response.add(new WarehouseResponse(warehouse));
        }

        return response;
    }

    @Transactional
    public void delete(Long warehouseId) {
        Warehouse warehouse = validate(warehouseId);
        List<Inventory> inventory = inventoryRepository.findAllByWarehouse(warehouse);

        for (Inventory inventoryItem : inventory) {
            if (inventoryItem.getWarehouse().getId() == warehouse.getId()) {
                inventoryRepository.delete(inventoryItem);
            }
        }

        warehouseRepository.deleteById(warehouseId);
    }
}
