package nakup.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse")
public class Warehouse {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;
    String address;

    LocalDateTime created;
    LocalDateTime updated;

    @OneToMany
    List<Inventory> inventory;
}
