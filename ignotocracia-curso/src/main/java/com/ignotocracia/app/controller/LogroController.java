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

import com.ignotocracia.app.service.LogroService;

@RestController
@RequestMapping("/api/logros")
@CrossOrigin
public class LogroController {
	
	@Autowired
	private LogroService logroService;
	
	
	/**Crear logro nuevo
	 * Solo para el administrador
	 * @param cronologia
	 * @return ResponseEntity<Cronologia> . Estado creado
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Logro> create(@RequestBody Logro logro){
		return ResponseEntity.status(HttpStatus.CREATED).body(logroService.save(logro));
	}
	
	/**Consultar un logro
	 * Comprueba que id existe 
	 * @param logroId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer logroId){

		Optional<Logro> logro = logroService.findById(logroId);
		
		if(!logro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(logro);
	}
	/**
	 * Editar logro
	 * @param logroDetails
	 * @param logroId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Logro logroDetails,@PathVariable(value="id") Integer logroId){
		
		Optional<Logro> logro=logroService.findById(logroId);
		
		if(!logro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		logro.get().setNombre(logroDetails.getNombre());

		return ResponseEntity.status(HttpStatus.CREATED).body(logroService.save(logro.get()));
	}
	/**
	 * Borrar logro
	 * @param logroId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Integer logroId ){
		
		if(!logroService.findById(logroId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		logroService.deleteById(logroId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}

	
	/**
	 * Ver todos los logros
	 * @return Lista logros
	 */
	@GetMapping
	public List<Logro> readAll(){
		
		 List<Logro> logros= StreamSupport
				 .stream(logroService.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return logros;
	}

}
