package com.cdj.api.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.Gostei;
import com.cdj.api.models.Views;
import com.cdj.api.repository.GosteiRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class GosteiResource {

	@Autowired
	GosteiRepository gosteiRepository;
	
	@GetMapping("/scores/{id}/likes")
	public ResponseEntity<?> numeroGosteis(@PathVariable(value="id") long id){
		List<Gostei> lista = gosteiRepository.findByScoreId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Likes", lista.size());
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@GetMapping("/likes")
	@JsonView(Views.External.class)
	public ResponseEntity<?> listaGosteis(){
		List<Gostei> lista = gosteiRepository.findAll();
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/likes")
	@JsonView(Views.External.class)
	public ResponseEntity<?> salvaGostei(@RequestBody Gostei gostei) {
		if (gosteiRepository.findByScoreIdAndUsuarioId(gostei.getScore().getId(), gostei.getUsuario().getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			gosteiRepository.save(gostei);
			return new ResponseEntity<Gostei>(gostei, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/likes")
	public ResponseEntity<?> deletaGostei(@RequestBody Gostei gostei){
		Gostei gosteiDeletar = gosteiRepository.findByScoreIdAndUsuarioId(gostei.getScore().getId(),gostei.getUsuario().getId());
		if (gosteiDeletar != null) {
			gosteiRepository.deleteById(gosteiDeletar.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
