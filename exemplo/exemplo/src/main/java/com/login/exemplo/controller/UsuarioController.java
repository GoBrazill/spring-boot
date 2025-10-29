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

import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

@CrossOrigin(origins = "http://127.0.0.1:5500")
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
	public ResponseEntity<?> login(@RequestBody Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
		if (findUser == null) {
			return ResponseEntity.ok("Usuario não encontrado");
		} else {
			if (findUser.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Logado com sucesso!");				
			} else {
				return ResponseEntity.ok("Senha incorreta");
			}
		}
		
	}
	
	@GetMapping(value = "listar/usuarios")
	public List<Usuario> listUsuarios(){
//		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarioRepository.findAll();
	}
	
	@GetMapping(value = "listar/usuario/{id}")
	public Optional<Usuario> usuarioPorId(@PathVariable int id){
		
		return usuarioRepository.findById(id);
	}
	
//	jeito que fiz inicialmente
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
	
	@DeleteMapping(value = "deletar/usuario/{id}")
	public ResponseEntity<?> deletar(@PathVariable int id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
	}
	
//	jeito da professora
//	public ResponseEntity<Void> deletar(@PathVariable int id) {
//		if (usuarioRepository.existsById(id)) {
//			usuarioRepository.deleteById(id);
//			return ResponseEntity.noContent().build(); // 204 status no content(usuario deletado com sucesso 
//		} else {
//			return ResponseEntity.notFound().build(); // 404 status de erro
//		}
//	}
	
	@PutMapping(value = "alterar/usuario/{id}")
	public ResponseEntity<?> atualizarDados(@PathVariable int id, @RequestBody Usuario novoUsuario){
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);
			
		if (UsuarioExistente.isPresent()) {
			Usuario usuario = UsuarioExistente.get();
			usuario.setName(novoUsuario.getName());
			usuario.setPassword(novoUsuario.getPassword());
			usuarioRepository.save(novoUsuario);
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
