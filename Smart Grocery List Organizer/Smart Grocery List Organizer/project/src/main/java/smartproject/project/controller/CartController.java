package smartproject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartproject.project.entities.Cart;
import smartproject.project.services.CartService;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Cart>> addCartList(@RequestBody List<Cart> cartList) {
        List<Cart> savedCarts = cartService.addCartList(cartList);
        return new ResponseEntity<>(savedCarts, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) {
        Cart cart = cartService.getCart(id);
        return cart != null 
            ? new ResponseEntity<>(cart, HttpStatus.OK) 
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cart>> getCartByName(@PathVariable String name) {
        List<Cart> carts = cartService.getCartsByName(name);
        return carts.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
            : new ResponseEntity<>(carts, HttpStatus.OK);
    }

   
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Cart>> sortCarts(@PathVariable String field) {
        List<Cart> sortedCarts = cartService.sort(field);
        return new ResponseEntity<>(sortedCarts, HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pagesize}")
    public ResponseEntity<List<Cart>> getCartsPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        List<Cart> paginatedCarts = cartService.page(offset, pagesize);
        return new ResponseEntity<>(paginatedCarts, HttpStatus.OK);
    }

   
    @GetMapping("/page/{offset}/{pagesize}/sort/{field}")
    public ResponseEntity<List<Cart>> getCartsPaginatedSorted(
            @PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<Cart> sortedPaginatedCarts = cartService.pageSort(offset, pagesize, field);
        return new ResponseEntity<>(sortedPaginatedCarts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> editCart(@PathVariable int id, @RequestBody Cart updateCart) {
        Cart cart = cartService.editCart(id, updateCart);
        return cart != null 
            ? new ResponseEntity<>(cart, HttpStatus.OK) 
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateCartByName(@PathVariable String name, @RequestParam Long totalPrice) {
        boolean updated = cartService.updateCartByName(name, totalPrice);
        return updated 
            ? new ResponseEntity<>("Cart updated successfully", HttpStatus.OK) 
            : new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        boolean isDeleted = cartService.deleteCart(id);
        return isDeleted 
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   
    @DeleteMapping("/deleteByPrice/{totalPrice}")
    public ResponseEntity<String> deleteCartByTotalPrice(@PathVariable Long totalPrice) {
        boolean deleted = cartService.deleteCartByTotalPrice(totalPrice);
        return deleted 
            ? new ResponseEntity<>("Cart(s) deleted successfully", HttpStatus.OK) 
            : new ResponseEntity<>("No cart(s) found with the given total price", HttpStatus.NOT_FOUND);
    }
}
