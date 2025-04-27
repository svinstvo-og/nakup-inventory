package nakup.inventory.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.HashMap;

public record ItemsReservedEvent(
        @JsonProperty("user-id")
        Long userId,
        @JsonProperty("order-id")
        Long orderId,
        @JsonProperty("created-at")
        Timestamp createdAt,
        boolean success
) { }
