package smartproject.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import smartproject.project.entities.Cart;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.name = ?1")
    List<Cart> findByName(String name);

   
    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.totalPrice = ?2 WHERE c.name = ?1")
    int updateCartByName(String name, Long totalPrice);

  
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.totalPrice = ?1")
    int deleteByTotalPrice(Long totalPrice);
}
