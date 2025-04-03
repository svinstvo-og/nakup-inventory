package nakup.inventory.service;

import nakup.inventory.dto.InventoryCreateRequest;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import nakup.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public void save(InventoryCreateRequest request) { //, Warehouse warehouse
        Inventory inventory = new Inventory();
        List<Warehouse> warehouses = new ArrayList<>();
        //warehouses.add(warehouse);

        inventory.setQuantity(request.getQuantity());
        inventory.setWarehouse(warehouses);
        inventory.setProductId(request.getProductId());
        inventoryRepository.save(inventory);
    }
}
