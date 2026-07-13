package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.Product;
import com.rewear.entity.Review;
import com.rewear.service.ProductService;
import com.rewear.service.ReviewService;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    // ==========================
    // Save Review
    // ==========================

    @PostMapping("/saveReview")
    public String saveReview(

            @RequestParam("productId") Long productId,
            @RequestParam("userName") String userName,
            @RequestParam("rating") int rating,
            @RequestParam("review") String reviewText) {

        Product product = productService.getProductById(productId);

        Review review = new Review();

        review.setProduct(product);
        review.setUserName(userName);
        review.setRating(rating);
        review.setReview(reviewText);

        reviewService.saveReview(review);

        return "redirect:/viewProduct/" + productId;
    }

}