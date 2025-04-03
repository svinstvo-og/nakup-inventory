package nakup.inventory.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreateRequest {
    private Long quantity;
    private Long warehouseId;
    private Long productId;
}
