package com.rewear.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.User;
import com.rewear.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public long getTotalUsers() {
        return userRepository.count();
    }

    // Register User
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        return userRepository.save(user);
    }

    // Login User
    public User loginUser(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    // Get User by Email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

	public User saveUser(User user) {
    return userRepository.save(user);
}
}