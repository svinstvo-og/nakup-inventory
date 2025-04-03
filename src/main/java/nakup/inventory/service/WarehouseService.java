package nakup.inventory.service;

import nakup.inventory.dto.WarehouseAddRequest;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse validate(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).orElse(null);
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
}
