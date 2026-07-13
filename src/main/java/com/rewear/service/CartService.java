package com.rewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Cart;
import com.rewear.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add Product to Cart
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get Cart Items by User Email
    public List<Cart> getCartItems(String userEmail) {
        return cartRepository.findByUserEmail(userEmail);
    }

    // Get All Cart Items
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Delete Cart Item
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    // Clear Cart
    public void clearCart() {
        cartRepository.deleteAll();
    }

}