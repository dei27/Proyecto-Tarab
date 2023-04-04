package com.tarab.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarab.core.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
	
	Usuario findByEmail(String email);

}
