package com.rewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Product;
import com.rewear.entity.Review;
import com.rewear.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Save Review
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Get Reviews of Product
    public List<Review> getReviews(Product product) {
        return reviewRepository.findByProduct(product);
    }

    // Average Rating
    public Double getAverageRating(Product product) {

        Double avg = reviewRepository.getAverageRating(product);

        return avg == null ? 0.0 : avg;
    }

    // Total Reviews
    public long getReviewCount(Product product) {
        return reviewRepository.countByProduct(product);
    }

}