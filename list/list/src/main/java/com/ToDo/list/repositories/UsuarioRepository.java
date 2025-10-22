package com.ToDo.list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDo.list.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
}
