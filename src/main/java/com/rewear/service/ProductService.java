package com.rewear.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Product;
import com.rewear.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    
    
    public long getTotalProducts() {
        return productRepository.count();
    }
    
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }
    
    
    public List<Product> filterProducts(

    		String category,

    		String brand,

    		String size,

    		String condition,

    		String purpose,

    		Double price){

    		return productRepository.filterProducts(

    		category,

    		brand,

    		size,

    		condition,

    		purpose,

    		price);

    		}

    public List<Product> getPriceLowToHigh() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> getPriceHighToLow() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    // Save Product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Update Product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete Product
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

 // Get Products By Seller Email
    public List<Product> getProductsBySellerEmail(String sellerEmail) {
        return productRepository.findBySellerEmail(sellerEmail);
    }

    public List<Product> getSellProducts() {
        return productRepository.findByPurposeIgnoreCase("Sell");
    }

    public List<Product> getExchangeProducts() {
        return productRepository.findByPurposeIgnoreCase("Exchange");
    }

    public List<Product> getDonateProducts() {
        return productRepository.findByPurposeIgnoreCase("Donate");
    }

}