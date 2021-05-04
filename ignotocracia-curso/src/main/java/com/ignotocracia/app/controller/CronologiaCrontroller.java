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

import com.ignotocracia.app.entity.Cronologia;
import com.ignotocracia.app.entity.User;
import com.ignotocracia.app.service.CronologiaService;


@RestController
@RequestMapping("/api/cronologia")
@CrossOrigin
public class CronologiaCrontroller {
	
	@Autowired
	private CronologiaService cronologiaService;
	
	
	/**Crear cronología nueva
	 * Solo para el administrador
	 * @param cronologia
	 * @return ResponseEntity<Cronologia> . Estado creado
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Cronologia> create(@RequestBody Cronologia cronologia){
		return ResponseEntity.status(HttpStatus.CREATED).body(cronologiaService.save(cronologia));
	}
	
	/**Consultar una cronologia
	 * Comprueba que id existe 
	 * @param userId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer cronologiaId){

		Optional<Cronologia> c = cronologiaService.findById(cronologiaId);
		
		if(!c.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(c);
	}
	/**
	 * Editar cronologia
	 * @param userDetails
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cronologia cronologiaDetails,@PathVariable(value="id") Integer cronologiaId){
		
		Optional<Cronologia> cronologia=cronologiaService.findById(cronologiaId);
		
		if(!cronologia.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		cronologia.get().setNombre(cronologiaDetails.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(cronologiaService.save(cronologia.get()));
	}
	/**
	 * Borrar cronología
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Integer cronologiaId ){
	//Optional<Cronologia> cronologia=cronologiaService.findById(cronologiaId);
		
		if(!cronologiaService.findById(cronologiaId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cronologiaService.deleteById(cronologiaId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}

	
	/**
	 * Ver todas las cronologias
	 * @return Lista cronologias
	 */
	@GetMapping
	public List<Cronologia> readAll(){
		
		 List<Cronologia> cronologias= StreamSupport
				 .stream(cronologiaService.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return cronologias;
	}


}
