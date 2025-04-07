package smartproject.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartproject.project.entities.Order;
import smartproject.project.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(int id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setDate(updatedOrder.getDate());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
