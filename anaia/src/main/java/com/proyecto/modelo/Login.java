package com.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Login")
public class Login {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_login")
	private int idLogin;

	@Column(name = "mail")
	private String mail;

	@Column(name = "contraseña")
	private String contraseña;

	public Login() {}

	public Login(String mail, String contraseña) {
		this.mail = mail;
		this.contraseña = contraseña;
	}

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}
