package com.cdj.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.Usuario;
import com.cdj.api.models.Views;
import com.cdj.api.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class UsuarioResource {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	@JsonView(Views.Internal.class)
	public Page<Usuario> listaUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	@GetMapping("/usuarios/{id}")
	@JsonView(Views.Internal.class)
	public Usuario listaUsuarioUnico(@PathVariable(value="id") long id){
		return usuarioRepository.findById(id);
	}
	
	@PostMapping("/usuarios")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@DeleteMapping("/usuarios/{id}")
	@JsonView(Views.External.class)
	public void deletaUsuario(@PathVariable(value="id") long id) {
		usuarioRepository.deleteById(id);
	}
	
	@PutMapping("/usuarios")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario) {
		if (usuarioRepository.findById(usuario.getId()) != null) {
			usuarioRepository.save(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/login")
	@JsonView(Views.External.class)
	public ResponseEntity<?> logaUsuario(@RequestBody Usuario usuario) {
		Usuario login = usuarioRepository.findByNicknameAndSenhaEquals(usuario.getNickname(), usuario.getSenha());
		if (login != null) {
			return new ResponseEntity<Usuario>(login, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
