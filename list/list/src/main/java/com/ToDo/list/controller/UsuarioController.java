package com.ToDo.list.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@PostMapping("/OwO")
	public String UwU(@RequestBody String UwU){
		return "-_-";
	}
//	@Autowired
//	UsuarioRepository usuarioRepository;
//	
//	@GetMapping
//	public List<Usuario> mostrarTudo() {
//		List<Usuario> li = usuarioRepository.findAll(); 
//		return li;
//	}
}
