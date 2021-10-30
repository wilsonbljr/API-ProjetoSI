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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.Favorito;
import com.cdj.api.models.Views;
import com.cdj.api.repository.FavoritoRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class FavoritoResource {

	@Autowired
	FavoritoRepository favoritoRepository;
	
	@GetMapping("/usuarios/{id}/jogos")
	@JsonView(Views.Intermediaria2.class)
	public Page<Favorito> listaJogosPorUsuario(@PathVariable(value="id") long id, Pageable pageable){ 
		return favoritoRepository.findByUsuarioid(id, pageable);
	}
	
	@GetMapping("/jogos/{id}/usuarios")
	@JsonView(Views.Intermediaria1.class)
	public Page<Favorito> listaUsuariosPorJogo(@PathVariable(value="id") long id, Pageable pageable){
		return favoritoRepository.findByJogoid(id, pageable);
	}
	
	@PostMapping("/favorito")
	@JsonView(Views.Intermediaria3.class)
	public ResponseEntity<?> salvaFavorito(@RequestBody Favorito favorito) {
		if (favoritoRepository.findByJogoidAndUsuarioid(favorito.getJogoid(), favorito.getUsuarioid()) != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			favoritoRepository.save(favorito);
			return new ResponseEntity<Favorito>(favorito, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/favorito")
	public ResponseEntity<?> deletaListaJogo(@RequestBody Favorito favorito){
		Favorito favoritoDeletar = favoritoRepository.findByJogoidAndUsuarioid(favorito.getJogoid(), favorito.getUsuarioid());
		if (favoritoDeletar != null) {
			favoritoRepository.deleteById(favoritoDeletar.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/favorito/validacao")
	public Boolean validaFavorito(@RequestBody Favorito favorito) {
		if (favoritoRepository.findByJogoidAndUsuarioid(favorito.getJogoid(), favorito.getUsuarioid()) != null) {
			return true;
		} else {
			return false;
		}
	}
}	
