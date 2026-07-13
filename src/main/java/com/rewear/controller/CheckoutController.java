package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rewear.service.CartService;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout(Model model) {

        // Cart Items
        model.addAttribute("cartItems", cartService.getAllCartItems());

        // Calculate Total Price
        double totalPrice = cartService.getAllCartItems()
                .stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();

        model.addAttribute("totalPrice", totalPrice);

        return "checkout";
    }

}