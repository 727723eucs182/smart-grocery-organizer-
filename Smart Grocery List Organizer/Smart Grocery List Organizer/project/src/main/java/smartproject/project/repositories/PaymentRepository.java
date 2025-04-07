package smartproject.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import smartproject.project.entities.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.paymentId = :paymentId")
    Optional<Payment> findByPaymentId(int paymentId);

    @Query("SELECT p FROM Payment p")
    List<Payment> findAllPayments();

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.paymentMethod = :paymentMethod WHERE p.paymentId = :paymentId")
    int updatePaymentMethod(int paymentId, String paymentMethod);

    
    @Modifying
    @Transactional
    @Query("DELETE FROM Payment p WHERE p.paymentId = :paymentId")
    int deletePaymentById(int paymentId);
}
