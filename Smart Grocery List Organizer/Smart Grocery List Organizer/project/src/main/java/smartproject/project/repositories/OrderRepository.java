package smartproject.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import smartproject.project.entities.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findByIdJPQL(int id);

    @Query("SELECT o FROM Order o")
    List<Order> findAllOrders();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.date = :date WHERE o.id = :id")
    int updateOrderDateById(int id, LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.id = :id")
    int deleteOrderById(int id);
}
