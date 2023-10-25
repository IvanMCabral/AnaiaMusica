package com.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.User;
import com.proyecto.repositorios.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        System.out.print(user.getFullName());
        return user.isPasswordValid(password);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}