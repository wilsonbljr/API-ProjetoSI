package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Page<Usuario> findAll(Pageable pageable);
	
	Usuario findById(long id);
	
	Usuario findByNicknameAndSenhaEquals(String nickname, String senha);
}
