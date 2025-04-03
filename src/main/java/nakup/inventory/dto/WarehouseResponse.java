package nakup.inventory.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponse {
    Long id;

    String name;
    String address;

    List<Long> inventory;

    public WarehouseResponse (Warehouse warehouse) {
        this.id = warehouse.getId();
        this.name = warehouse.getName();
        this.address = warehouse.getAddress();

        List<Inventory> inventories = warehouse.getInventory();
        List<Long> inventoryIds = new ArrayList<>();

        for (Inventory inventory : inventories) {
            inventoryIds.add(inventory.getId());
        }

        this.inventory = inventoryIds;
    }
}
