package nakup.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    Long id;
    @JsonProperty("product-id")
    Long productId;
    Long quantity;
    @JsonProperty("warehouse-id")
    Long warehouseId;

    public InventoryResponse (Inventory inventory) {
        this.id = inventory.getId();
        this.productId = inventory.getProductId();
        this.quantity = inventory.getQuantity();
        this.warehouseId = inventory.getWarehouse().getId();
    }
}