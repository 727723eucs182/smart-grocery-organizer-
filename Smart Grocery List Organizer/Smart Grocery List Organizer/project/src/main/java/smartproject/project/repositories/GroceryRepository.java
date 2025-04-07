package smartproject.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import smartproject.project.entities.Grocery;

public interface GroceryRepository extends JpaRepository<Grocery,Integer>{

    @Query("SELECT g FROM Grocery g WHERE g.name = :name")
    List<Grocery> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Grocery g SET g.price = :price WHERE g.name = :name")
    int updateGroceryPriceByName(String name, Long price);

    @Modifying
    @Transactional
    @Query("DELETE FROM Grocery g WHERE g.name = :name")
    int deleteByName(String name);

   
    @Query("SELECT COUNT(g) > 0 FROM Grocery g WHERE g.name = :name")
    boolean existsByName(String name);


}
