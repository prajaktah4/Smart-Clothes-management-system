package com.rewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Order;
import com.rewear.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    
    public long getTotalOrders() {
        return orderRepository.count();
    }

    // Save Order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get Order By ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // Get Orders By Customer Email
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    // Get Orders By Status
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    // Update Order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete Order
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}