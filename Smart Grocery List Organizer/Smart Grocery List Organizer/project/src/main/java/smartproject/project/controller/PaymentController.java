package smartproject.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartproject.project.entities.Payment;
import smartproject.project.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/post")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.addPayment(payment), HttpStatus.CREATED);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment != null ? new ResponseEntity<>(payment, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePayment(id, payment.getAmount(), payment.getPaymentDate(), payment.getPaymentMethod());
        return updatedPayment != null ? new ResponseEntity<>(updatedPayment, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id) {
        return paymentService.deletePayment(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
