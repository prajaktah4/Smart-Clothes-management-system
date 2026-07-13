package com.rewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Wishlist;
import com.rewear.repository.WishlistRepository;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    
    
    public List<Wishlist> getAllWishlist() {
        return wishlistRepository.findAll();
    }

    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getWishlist(String userEmail) {
        return wishlistRepository.findByUserEmail(userEmail);
    }

    public void deleteWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

	public Wishlist getWishlistById(Long id) {
    return wishlistRepository.findById(id).orElse(null);
}
}