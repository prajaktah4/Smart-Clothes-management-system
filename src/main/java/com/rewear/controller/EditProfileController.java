package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rewear.entity.User;
import com.rewear.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EditProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/edit-profile")
    public String editProfile(HttpSession session, Model model) {

        // Get logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        // Get latest user details from database
        User dbUser = userService.getUserByEmail(user.getEmail());

        model.addAttribute("user", dbUser);

        return "edit-profile";
    }
    
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute User user,
                                HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        User existingUser = userService.getUserByEmail(loggedInUser.getEmail());

        existingUser.setName(user.getName());
        existingUser.setMobile(user.getMobile());
        existingUser.setGender(user.getGender());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setPincode(user.getPincode());

        userService.saveUser(existingUser);

        session.setAttribute("loggedInUser", existingUser);

        return "redirect:/profile";
    }
}