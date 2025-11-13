package com.login.exemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.dto.UsuarioUpdateDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.service.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

//	ResponseEntity<?> retorna uma mensagem com um protocolo http
	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
		return ResponseEntity.status(HttpStatus.CREATED).
				body(usuarioService.saveUser(user));
	}

	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody UsuarioRequestDTO user) {
		
		return ResponseEntity.ok(usuarioService.login(user));
	}

	@GetMapping(value = "listar/usuarios")
	public List<UsuarioResponseDTO> listUsuarios() {
		
		return usuarioService.listUsuarios();
	}

	@GetMapping(value = "listar/usuario/{id}")
	public Optional<Usuario> usuarioPorId(@PathVariable int id) {

		return usuarioService.usuarioPorId(id);
	}

//	jeito que fiz inicialmente (só aparece a mensagem dentro do console, sempre mandando o código 200 para o postman maesmo se o usuario não existir
//	@DeleteMapping(value = "delete/usuario/{id}")
//	public void deletePorId(@PathVariable int id){
//		if (usuarioRepository.existsById(id) == true){
//			
// 			System.out.println("Usuário deletado com sucesso");
//		} else {
//			
//			System.out.println("Usuário não encontrado");
//		}
//	}

//	jeitos da professora
	@DeleteMapping(value = "deletar/usuario/{id}")
	public ResponseEntity<?> deletar(@PathVariable int id) {

		return ResponseEntity.ok(usuarioService.deletar(id));
	}

//	public ResponseEntity<Void> deletar(@PathVariable int id) {
//		if (usuarioRepository.existsById(id)) {
//			usuarioRepository.deleteById(id);
//			return ResponseEntity.noContent().build(); // 204 status no content(usuario deletado com sucesso 
//		} else {
//			return ResponseEntity.notFound().build(); // 404 status de erro
//		}
//	}

	@PutMapping(value = "alterar/usuario/{id}")
	public ResponseEntity<?> atualizarDados(@PathVariable int id, @Valid @RequestBody UsuarioUpdateDTO novoUsuario) {
		
		return ResponseEntity.ok(usuarioService.atualizarDados(id, novoUsuario));
	}
}
