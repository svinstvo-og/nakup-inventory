package nakup.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreateRequest {
    private Long quantity;
    @JsonProperty("warehouse-id")
    private Long warehouseId;
    @JsonProperty("product-id")
    private Long productId;
}
