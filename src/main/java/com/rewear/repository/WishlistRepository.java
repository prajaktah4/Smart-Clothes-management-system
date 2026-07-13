package com.rewear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewear.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserEmail(String userEmail);

}