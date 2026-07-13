package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rewear.entity.User;
import com.rewear.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        // Get logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        // If user is not logged in
        if (user == null) {
            return "redirect:/login";
        }

        // Get latest user details from database
        User dbUser = userService.getUserByEmail(user.getEmail());

        model.addAttribute("user", dbUser);

        return "profile";
    }
}