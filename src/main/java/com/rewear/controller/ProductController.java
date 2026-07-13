package com.rewear.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.rewear.entity.Product;
import com.rewear.entity.User;
import com.rewear.service.ExchangeService;
import com.rewear.service.OrderService;
import com.rewear.service.ProductService;
import com.rewear.service.ReviewService;
import com.rewear.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
    
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private ReviewService reviewService;

    // ================= Upload Page =================

    
    
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword,
                                 Model model) {

        model.addAttribute("products",
                productService.searchProducts(keyword));

        return "marketplace";
    }
    
    @GetMapping("/brand/{brand}")
    public String brand(@PathVariable String brand,
                        Model model) {

        model.addAttribute("products",
                productService.getProductsByBrand(brand));

        return "marketplace";
    }
    
    @GetMapping("/priceLow")
    public String priceLow(Model model) {

        model.addAttribute("products",
                productService.getPriceLowToHigh());

        return "marketplace";
    }
    
    @GetMapping("/priceHigh")
    public String priceHigh(Model model) {

        model.addAttribute("products",
                productService.getPriceHighToLow());

        return "marketplace";
    }
    
    
    @GetMapping("/filter")
    public String filter(

    @RequestParam(required=false) String category,

    @RequestParam(required=false) String brand,

    @RequestParam(required=false) String size,

    @RequestParam(required=false) String condition,

    @RequestParam(required=false) String purpose,

    @RequestParam(required=false) Double price,

    Model model){

    model.addAttribute("products",

    productService.filterProducts(

    category,

    brand,

    size,

    condition,

    purpose,

    price));

    return "marketplace";

    }
    
    @GetMapping("/category/{category}")
    public String category(@PathVariable String category,
                           Model model) {

        model.addAttribute("products",
                productService.getProductsByCategory(category));

        return "marketplace";
    }
    
    
    
    

    
    // ================= Marketplace (SELL ONLY) =================

    @GetMapping("/marketplace")
    public String marketplace(Model model) {

        List<Product> products = productService.getSellProducts();

        for (Product product : products) {

            product.setAverageRating(
                    reviewService.getAverageRating(product));

        }

        model.addAttribute("products", products);

        return "marketplace";
    }

    
 // ================= My Uploads =================

    @GetMapping("/my-uploads")
    public String myUploads(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Product> products = productService.getProductsBySellerEmail(user.getEmail());

        model.addAttribute("products", products);

        return "my-uploads";
    }
    
    
   
    // ================= Donate =================

    @GetMapping("/donate-clothes")
    public String donate(Model model) {

        model.addAttribute("products", productService.getDonateProducts());

        return "donate-clothes";
    }

    // ================= View Product =================

    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id,
                              Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        model.addAttribute("reviews",
                reviewService.getReviews(product));

        model.addAttribute("averageRating",
                reviewService.getAverageRating(product));

        model.addAttribute("reviewCount",
                reviewService.getReviewCount(product));

        return "view-product";
    }

    // ================= Edit Product =================

    
    
    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable Long id,
                              Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Product product = productService.getProductById(id);

        if (product == null) {
            return "redirect:/my-uploads";
        }

        // Allow only the owner to edit
        if (!product.getSellerEmail().equals(user.getEmail())) {
            return "redirect:/my-uploads";
        }

        model.addAttribute("product", product);

        return "edit-product";
    }
    
    
    // ================= Update Product =================

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product,
                                HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Product existingProduct =
                productService.getProductById(product.getProductId());

        if (existingProduct == null) {
            return "redirect:/my-uploads";
        }

        if (!existingProduct.getSellerEmail().equals(user.getEmail())) {
            return "redirect:/my-uploads";
        }

        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setSize(product.getSize());
        existingProduct.setCondition(product.getCondition());
        existingProduct.setPurpose(product.getPurpose());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStatus(product.getStatus());

        productService.saveProduct(existingProduct);

        return "redirect:/my-uploads";
    }

    // ================= Delete Product =================

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id,
                                HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Product product = productService.getProductById(id);

        if (product == null) {
            return "redirect:/my-uploads";
        }

        // Allow only the owner to delete
        if (!product.getSellerEmail().equals(user.getEmail())) {
            return "redirect:/my-uploads";
        }

        productService.deleteProduct(id);

        return "redirect:/my-uploads";
    }	
    
    
}