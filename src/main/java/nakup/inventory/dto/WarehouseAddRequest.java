package nakup.inventory.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseAddRequest {
    private String name;
    private String address;
}
