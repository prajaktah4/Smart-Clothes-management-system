package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.Cart;
import com.rewear.entity.Product;
import com.rewear.entity.Wishlist;
import com.rewear.service.CartService;
import com.rewear.service.ProductService;
import com.rewear.service.WishlistService;

@Controller
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    
    
    
    @GetMapping("/wishlist/cart/{id}")
    public String moveToCart(@PathVariable Long id) {

        Wishlist wishlist =
                wishlistService.getWishlistById(id);

        Cart cart = new Cart();

        cart.setProduct(wishlist.getProduct());

        cart.setUserEmail(wishlist.getUserEmail());

        cart.setQuantity(1);

        cartService.saveCart(cart);

        wishlistService.deleteWishlist(id);

        return "redirect:/cart";
    }

    
    //show wishlist
    
    @GetMapping("/wishlist")
    public String wishlist(Model model) {

        model.addAttribute("wishlistItems",
                wishlistService.getAllWishlist());

        return "wishlist";
    }
    
    //add product to wishlist

    @GetMapping("/wishlist/add/{id}")
    public String addWishlist(@PathVariable Long id) {

        Product product = productService.getProductById(id);

        Wishlist wishlist = new Wishlist();

        wishlist.setProduct(product);

        wishlist.setUserEmail("user@gmail.com");

        wishlistService.saveWishlist(wishlist);

        return "redirect:/wishlist";
    }

    
    //remove wishlist
    
    @GetMapping("/wishlist/delete/{id}")
    public String deleteWishlist(@PathVariable Long id) {

        wishlistService.deleteWishlist(id);

        return "redirect:/wishlist";
    }
}