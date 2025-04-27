package nakup.inventory.service;

import jakarta.transaction.Transactional;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Reserved;
import nakup.inventory.repository.InventoryRepository;
import nakup.inventory.repository.ReservedRepository;
import org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ReservedRepository reservedRepository;

    public void reserveOrder(Long orderId, HashMap<Long, Integer> items) {

        if (reservedRepository.existsByOrderId(orderId)) {
            throw new RuntimeException("Reservation already exists");
        }

        reduceInventory(items);

        addReservedInventory(orderId, items);
    }

    //Takes stock from inventory, throws error if impossible
    @Transactional
    public List<Inventory> reduceInventory(HashMap<Long, Integer> items) {
        List<Inventory> inventoryList = new ArrayList<>();

        for (Long productId : items.keySet().stream().toList()) {
            Inventory inventory = inventoryRepository.findByProductId(productId);
            if (inventory == null) {
                throw new RuntimeException("Product with id = " + productId + " does not exist in inventory");
            }
            if (inventory.getQuantity() < items.get(productId)) {
                throw new RuntimeException("Not enough stock for item: " + productId);
            }
            inventory.setQuantity(inventory.getQuantity() - items.get(productId));

            System.out.println("Took item from inventory: " + productId);

            inventoryRepository.save(inventory);
            inventoryList.add(inventory);
        }
        return inventoryList;
    }

    public void addReservedInventory(Long orderId, HashMap<Long, Integer> items) {

        Reserved reserved;

        for (Long productId : items.keySet().stream().toList()) {
            reserved = new Reserved();
            reserved.setOrderId(orderId);
            reserved.setProductId(productId);
            reserved.setQuantity(items.get(productId).longValue());

            reserved.setReservedAt(Timestamp.valueOf(LocalDateTime.now()));
            reserved.setValidUntil(Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)));

            reservedRepository.save(reserved);

            System.out.println("Product reserved: " + productId);
        }
    }
}
