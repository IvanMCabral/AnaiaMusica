package com.proyecto.anaia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.proyecto.controller.LoginController;
import com.proyecto.modelo.LoginResponse;
import com.proyecto.modelo.User;
import com.proyecto.repositorios.UserRepository;
import com.proyecto.service.AuthService;


public class TestLoginController {

	 	@Mock
	    private UserRepository userRepository;

	    @Mock
	    private AuthService authService;

	    @InjectMocks
	    private LoginController loginController;

	    
	    @Test
	    void testLogin() {
	        when(authService.authenticate(any(), any())).thenReturn(true);

	        User user = new User();
	        user.setFirstName("Test");
	        user.setLastName("User");
	        user.setEmail("test@example.com");

	        when(authService.findByEmail(any())).thenReturn(Optional.of(user));

	        Map<String, String> requestParams = new HashMap<>();
	        requestParams.put("email", "test@example.com");
	        requestParams.put("password", "testpassword");

	        LoginResponse response = loginController.login(requestParams);

	        assertEquals("Bienvenido Test User", response.getMessage());
	        assertEquals(user, response.getUser());
	    }

	    @Test
	    void testRegister() {
	        when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

	        Map<String, String> requestParams = new HashMap<>();
	        requestParams.put("firstName", "John");
	        requestParams.put("lastName", "Doe");
	        requestParams.put("email", "john.doe@example.com");
	        requestParams.put("password", "testpassword");

	        User registeredUser = loginController.register(requestParams);

	        assertEquals("John", registeredUser.getFirstName());
	        assertEquals("Doe", registeredUser.getLastName());
	        assertEquals("john.doe@example.com", registeredUser.getEmail());
	    }
}
