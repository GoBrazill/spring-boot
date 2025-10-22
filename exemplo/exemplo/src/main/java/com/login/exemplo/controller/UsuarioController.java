package com.login.exemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

@RestController	
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

//	ResponseEntity<?> retorna uma mensagem com um protocolo http
	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
		Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
		usuarioRepository.save(usuario);
		return ResponseEntity.ok("Usuario salvo!" + usuario.toString());
	}
	
	@PostMapping(value = "login")
	public ResponseEntity<?> login(Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
		if (findUser == null) {
			return ResponseEntity.ok("Usuario não encontrado");
		} else {
			if (findUser.getPassword() == user.getPassword()) {
				return ResponseEntity.ok("Logado com sucesso!");				
			} else {
				return ResponseEntity.ok("Senha incorreta");
			}
		}
		
	}
}
