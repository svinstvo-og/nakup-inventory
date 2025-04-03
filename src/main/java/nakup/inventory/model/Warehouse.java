package nakup.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;
    String address;

    @ManyToMany
    List<Inventory> inventory;
}
