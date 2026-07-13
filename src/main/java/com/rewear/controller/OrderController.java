package com.rewear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.Cart;
import com.rewear.entity.Order;
import com.rewear.service.CartService;
import com.rewear.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    // ===========================
    // Place Order
    // ===========================
    @PostMapping("/placeOrder")
    public String placeOrder(

            @RequestParam("customerName") String customerName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("pincode") String pincode,
            @RequestParam("paymentMethod") String paymentMethod) {

        List<Cart> cartItems = cartService.getAllCartItems();

        for (Cart cart : cartItems) {

            Order order = new Order();

            // Product
            order.setProduct(cart.getProduct());

            // Quantity
            order.setQuantity(cart.getQuantity());

            // Customer Details
            order.setCustomerName(customerName);
            order.setEmail(email);
            order.setPhone(phone);
            order.setAddress(address);
            order.setCity(city);
            order.setPincode(pincode);

            // Payment
            order.setPaymentMethod(paymentMethod);

            // Status
            order.setOrderStatus("Placed");

            orderService.saveOrder(order);
        }

        // Clear Cart
        cartService.clearCart();

        return "redirect:/my-orders";
    }

    // ===========================
    // My Orders
    // ===========================
    @GetMapping("/my-orders")
    public String myOrders(Model model) {

        model.addAttribute("orders", orderService.getAllOrders());

        return "orders";
    }

    // ===========================
    // View Order
    // ===========================
    @GetMapping("/viewOrder/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {

        model.addAttribute("order",
                orderService.getOrderById(id));

        return "view-order";
    }

    // ===========================
    // Delete Order
    // ===========================
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);

        return "redirect:/my-orders";
    }

}