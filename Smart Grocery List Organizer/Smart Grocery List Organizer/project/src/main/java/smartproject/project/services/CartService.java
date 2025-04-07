package smartproject.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartproject.project.entities.Cart;
import smartproject.project.repositories.CartRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> addCartList(List<Cart> cartList) {
        return cartRepository.saveAll(cartList);
    }

    // ✅ Sorting Method (No Change)
    public List<Cart> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return cartRepository.findAll(sort);
    }

    // ✅ Pagination Method (No Change)
    public List<Cart> page(int pageSize, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return cartRepository.findAll(pageable).getContent();
    }

    // ✅ Pagination + Sorting Method (No Change)
    public List<Cart> pageSort(int pageSize, int pageNo, String field) {
        Pageable pageable = PageRequest.of(pageNo, pageSize).withSort(Sort.by(Sort.Direction.ASC, field));
        return cartRepository.findAll(pageable).getContent();
    }

    // ✅ Get Cart by ID
    public Cart getCart(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    // ✅ Get Carts by Name
    public List<Cart> getCartsByName(String name) {
        return cartRepository.findByName(name);
    }

    // ✅ Update Cart by Name (Custom JPQL)
    @Transactional
    public boolean updateCartByName(String name, Long totalPrice) {
        int rowsUpdated = cartRepository.updateCartByName(name, totalPrice);
        return rowsUpdated > 0;
    }

    // ✅ Delete Cart by Total Price (Custom JPQL)
    @Transactional
    public boolean deleteCartByTotalPrice(Long totalPrice) {
        int rowsDeleted = cartRepository.deleteByTotalPrice(totalPrice);
        return rowsDeleted > 0;
    }

    // ✅ Update Cart by ID
    public Cart editCart(int id, Cart updatedCart) {
        Cart existingCart = cartRepository.findById(id).orElse(null);
        if (existingCart != null) {
            existingCart.setName(updatedCart.getName());
            existingCart.setTotalPrice(updatedCart.getTotalPrice());
            return cartRepository.save(existingCart);
        }
        return null;
    }

    // ✅ Delete Cart by ID
    public boolean deleteCart(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
