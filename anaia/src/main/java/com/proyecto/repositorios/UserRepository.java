package com.proyecto.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByEmail(String email);
}