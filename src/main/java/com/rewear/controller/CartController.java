package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.Cart;
import com.rewear.entity.Product;
import com.rewear.service.CartService;
import com.rewear.service.ProductService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    // Add product to cart
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {

        Product product = productService.getProductById(id);

        Cart cart = new Cart();

        cart.setProduct(product);

        // Replace with logged-in user's email later
        cart.setUserEmail("user@gmail.com");

        cart.setQuantity(1);

        cartService.saveCart(cart);

        return "redirect:/cart";
    }

    // Display Cart
    @GetMapping("/cart")
    public String cart(Model model) {

        model.addAttribute(
                "cartItems",
                cartService.getCartItems("user@gmail.com"));

        return "cart";
    }

    // Remove Item
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable Long id) {

        cartService.deleteCart(id);

        return "redirect:/cart";
    }

}