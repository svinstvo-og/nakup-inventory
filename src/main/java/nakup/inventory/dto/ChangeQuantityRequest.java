package nakup.inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeQuantityRequest {
    @JsonProperty("product-id")
    private Long productId;
    @JsonProperty("change-quantity-by")
    private Long changeQuantityBy;
    @JsonProperty("change-quantity")
    private Long changeQuantity;
}
