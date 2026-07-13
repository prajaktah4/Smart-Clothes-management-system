package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rewear.service.ProductService;

@Controller
public class PageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/user-dashboard")
    public String userDashboard(Model model) {

        model.addAttribute("products",
                productService.getAllProducts());

        return "user-dashboard";
    }


    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "Admin-Dashboard";
    }

    @GetMapping("/ngo-dashboard")
    public String ngoDashboard() {
        return "ngo-dashboard";
    }
}