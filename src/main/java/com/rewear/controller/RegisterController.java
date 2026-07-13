package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rewear.entity.User;
import com.rewear.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

   

    // Open Registration Page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               RedirectAttributes redirectAttributes) {

        try {

            userService.registerUser(user);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Registration Successful! Please login.");

            return "redirect:/login";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage());

            return "redirect:/register";
        }
    }
    
   
}