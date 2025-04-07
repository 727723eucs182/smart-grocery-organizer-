package smartproject.project.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartproject.project.entities.Payment;
import smartproject.project.repositories.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment updatePayment(int paymentId, double amount, String paymentDate, String paymentMethod) {
        Payment existingPayment = paymentRepository.findById(paymentId).orElse(null);
        if (existingPayment != null) {
            existingPayment.setAmount(amount);
            existingPayment.setPaymentDate(paymentDate);
            existingPayment.setPaymentMethod(paymentMethod);
            return paymentRepository.save(existingPayment);
        }
        return null;
    }

    public boolean deletePayment(int paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
            return true;
        }
        return false;
    }
}
