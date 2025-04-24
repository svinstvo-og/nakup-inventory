package nakup.inventory.repository;

import nakup.inventory.model.Reserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedRepository extends JpaRepository<Reserved, Long> {
    public boolean existsByOrderId(Long orderId);
    public Reserved findByOrderId(Long orderId);
}
