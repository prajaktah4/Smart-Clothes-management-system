package com.rewear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewear.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByPurposeIgnoreCase(String purpose);

    List<Product> findBySellerEmail(String sellerEmail);
    
    

    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByBrandIgnoreCase(String brand);
    
    

    

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();
    
    @Query("""
            SELECT p FROM Product p
            WHERE
            (:category IS NULL OR :category='' OR p.category=:category)
            AND
            (:brand IS NULL OR :brand='' OR p.brand=:brand)
            AND
            (:size IS NULL OR :size='' OR p.size=:size)
            AND
            (:condition IS NULL OR :condition='' OR p.condition=:condition)
            AND
            (:purpose IS NULL OR :purpose='' OR p.purpose=:purpose)
            AND
            (:price IS NULL OR p.price<=:price)
            """)
        List<Product> filterProducts(
                @Param("category") String category,
                @Param("brand") String brand,
                @Param("size") String size,
                @Param("condition") String condition,
                @Param("purpose") String purpose,
                @Param("price") Double price);
    }
 