package com.rewear.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.User;
import com.rewear.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {

        User user = userService.loginUser(email, password);

        if (user != null) {

            session.setAttribute("loggedInUser", user);

            return "redirect:/user-dashboard";

        } else {

            model.addAttribute("error", "Invalid Email or Password");

            return "login";
        }
    }
}