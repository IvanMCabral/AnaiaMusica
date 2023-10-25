package com.proyecto.modelo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Column(name = "last_name")
	private String lastName;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "password")
	private String password;
	

	 public boolean isPasswordValid(String password) {
	        // Verifica si la contraseña proporcionada es la misma que la almacenada en forma de hash
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();		
	        return passwordEncoder.matches(password, this.password);
	    }
	 
	 
	 public void hashPassword(String password) {
		    this.password = password;
		}
	

	  public String getFullName() {
		    return this.firstName + " " + this.lastName;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	  

	  
	
	}