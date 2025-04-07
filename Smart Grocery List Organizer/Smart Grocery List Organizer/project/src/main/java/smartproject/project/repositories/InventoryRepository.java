package smartproject.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import smartproject.project.entities.Inventory;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // ✅ Get Inventory by Item Name (JPQL Query)
    @Query("SELECT i FROM Inventory i WHERE i.itemName = ?1")
    List<Inventory> findByItemName(String itemName);

    // ✅ Get Inventory by Unit (Derived Query Method)
    List<Inventory> findByUnit(String unit);

    // ✅ Update Quantity by Item Name (JPQL Query)
    @Transactional
    @Modifying
    @Query("UPDATE Inventory i SET i.quantity = ?2 WHERE i.itemName = ?1")
    int updateQuantityByItemName(String itemName, int quantity);

    // ✅ Delete Inventory by Unit (JPQL Query)
    @Transactional
    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.unit = ?1")
    int deleteByUnit(String unit);
}
