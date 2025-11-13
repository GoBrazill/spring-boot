package com.login.exemplo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.dto.UsuarioUpdateDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioResponseDTO saveUser(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = new Usuario(usuarioRequestDTO.getName(), 
				usuarioRequestDTO.getEmail(), usuarioRequestDTO.getPassword());
		usuarioRepository.save(usuario);
		UsuarioResponseDTO user = new UsuarioResponseDTO(usuario);
		return user;
	}
	
	public String login(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario findUser = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
		if (findUser == null) {
			return "Usuario não encontrado";
		} else {
			if (findUser.getPassword().equals(usuarioRequestDTO.getPassword())) {
				return "Logado com sucesso!";
			} else {
				return "Senha incorreta";
			}
		}
		
	}
	
	public List<UsuarioResponseDTO> listUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioResponseDTO> listaDeUsuarios =  new ArrayList<>();
		
		listaDeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
		
		return listaDeUsuarios;
	}
	
	public Optional<Usuario> usuarioPorId(int id) {

		return usuarioRepository.findById(id);
	}
	
	public String deletar(int id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return "Excluído com sucesso";
		} else {
			return "Usuário não encontrado";
		}
	}
	
	public String atualizarDados(int id, UsuarioUpdateDTO novoUsuario) {
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario usuario = UsuarioExistente.get();
			usuario.setName(novoUsuario.getName());
			usuario.setPassword(novoUsuario.getPassword());
			usuarioRepository.save(usuario);
			return "Nome e senha alterados com sucesso" + "\n id: " + usuario.getId() + "\n name: " + usuario.getName() + "\n email: " + usuario.getEmail();
		} else {
			return "Esse ID não foi encontrado";
		}
	}
}