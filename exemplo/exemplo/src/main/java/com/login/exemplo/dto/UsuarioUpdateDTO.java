package com.login.exemplo.dto;

import com.login.exemplo.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateDTO {
	
	@NotBlank(message = "O nome não pode estar vazio")
	private String name;
	@Size(min = 6, max = 20, message = "A senha tem que esta entre 6 e 20 caracteres seu mocorongo")
	private String password;
	
	public UsuarioUpdateDTO() {
		
	}
	
	public UsuarioUpdateDTO(Usuario user) {
		this.name = user.getName();
		this.password = user.getPassword();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
