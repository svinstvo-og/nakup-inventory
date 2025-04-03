package nakup.inventory.repository;

import nakup.inventory.dto.WarehouseResponse;
import nakup.inventory.model.Inventory;
import nakup.inventory.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Inventory findByProductId(Long id);

    public List<Inventory> findAllByWarehouse(Warehouse warehouse);
}
