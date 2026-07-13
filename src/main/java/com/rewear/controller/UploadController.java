package com.rewear.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rewear.entity.Product;
import com.rewear.entity.User;
import com.rewear.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

    @Autowired
    private ProductService productService;

    // ================= Upload Clothes Page =================

    @GetMapping("/upload-clothes")
    public String uploadPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("product", new Product());

        return "upload-clothes";
    }

    // ================= Save Product =================

    @PostMapping("/saveProduct")
    @ResponseBody
    public String saveProduct(@RequestParam("imageFile") MultipartFile file) {

        if (file.isEmpty()) {
            return "No file selected";
        }

        return "File received: " + file.getOriginalFilename();
    }
}