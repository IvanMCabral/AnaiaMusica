package com.proyecto.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.LoginResponse;
import com.proyecto.modelo.User;
import com.proyecto.repositorios.UserRepository;
import com.proyecto.service.AuthService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class LoginController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
	private AuthService authService;


    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody Map<String, String> requestParams) {
        String email = requestParams.get("email");
        String password = requestParams.get("password");

        boolean isAuthenticated = authService.authenticate(email, password);
        if (!isAuthenticated) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Usuario y/o contraseña incorrectos");
            return response;
        }

        Optional<User> optionalUser = authService.findByEmail(email);
        if (!optionalUser.isPresent()) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Usuario no encontrado");
            return response;
        }

        User user = optionalUser.get();
        LoginResponse response = new LoginResponse();
        response.setMessage("Bienvenido " + user.getFullName());
        response.setUser(user);
        return response;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String,String> requestParams) {
        User user = new User();
        user.setFirstName(requestParams.get("firstName"));
        user.setLastName(requestParams.get("lastName"));
        user.setEmail(requestParams.get("email"));
        
        // Cifrar la contraseña
        String encodedPassword = passwordEncoder.encode(requestParams.get("password"));
        user.hashPassword(encodedPassword);
        
        // Guardar el usuario en la base de datos
        return userRepository.save(user);

    }
}
