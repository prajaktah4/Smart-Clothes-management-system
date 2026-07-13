package com.rewear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewear.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get all orders
    List<Order> findAll();

    // Get orders by customer email
    List<Order> findByEmail(String email);

    // Get orders by status
    List<Order> findByOrderStatus(String orderStatus);

}