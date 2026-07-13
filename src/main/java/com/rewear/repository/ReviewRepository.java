package com.rewear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rewear.entity.Product;
import com.rewear.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Get all reviews of a product
    List<Review> findByProduct(Product product);

    // Average rating of a product
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product = ?1")
    Double getAverageRating(Product product);

    // Total reviews of a product
    long countByProduct(Product product);

}