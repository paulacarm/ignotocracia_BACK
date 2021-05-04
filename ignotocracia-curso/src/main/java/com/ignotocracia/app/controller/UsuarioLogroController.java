package com.ignotocracia.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignotocracia.app.entity.Logro;
import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.service.UsuarioLogroService;

@RestController
@RequestMapping("/api/logrosusuario")
@CrossOrigin
public class UsuarioLogroController {

	@Autowired
	UsuarioLogroService ulservice;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<UsuarioLogro> create(@RequestBody UsuarioLogro ul){
		return ResponseEntity.status(HttpStatus.CREATED).body(ulservice.save(ul));
	}
	
	
	
	@CrossOrigin
	@GetMapping
	public List<UsuarioLogro> readAll(){
		 List<UsuarioLogro> logrosusuario= StreamSupport
				 .stream(ulservice.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return logrosusuario;
	}
	

	/**Consultar un logro
	 * Comprueba que id existe 
	 * @param logroId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id")  UsuarioLogroId UsuarioLogroId){

		Optional<UsuarioLogro> ul = ulservice.findById(UsuarioLogroId);
		
		if(!ul.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ul);
	}
	/**
	 * Editar logro
	 * @param logroDetails
	 * @param logroId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UsuarioLogro ulDetails,@PathVariable(value="id") UsuarioLogroId UsuarioLogroId){
		
		Optional<UsuarioLogro> ul=ulservice.findById(UsuarioLogroId);
		
		if(!ul.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		ul.get().setLogro(ulDetails.getLogro());
		ul.get().setUsuario(ulDetails.getUsuario());
		ul.get().setPuntos(ulDetails.getPuntos());

		return ResponseEntity.status(HttpStatus.CREATED).body(ulservice.save(ul.get()));
	}
	/**
	 * Borrar logro
	 * @param logroId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") UsuarioLogroId UsuarioLogroId){
		
		if(!ulservice.findById(UsuarioLogroId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ulservice.deleteById(UsuarioLogroId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
}
