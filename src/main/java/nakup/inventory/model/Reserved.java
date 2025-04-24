package nakup.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.bouncycastle.util.Times;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "reserved")
public class Reserved {
    @Id
    Long orderId;

    Long productId;
    Long quantity;

    Timestamp reservedAt;
    Timestamp validUntil;

    @ManyToOne(fetch = FetchType.LAZY)
    Warehouse warehouse;
}
