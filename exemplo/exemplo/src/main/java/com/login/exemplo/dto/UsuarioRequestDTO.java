package com.login.exemplo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
	
	
	@NotBlank(message = "O nome não poder ser nulo")
	private String name;
	
	@NotBlank(message = "Esse email tá errado paizão")
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 20, message = "A senha dever ter entre 6 a 20 caracteres")
	private String password;
	
	public UsuarioRequestDTO() {
		
	}

	public UsuarioRequestDTO(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
