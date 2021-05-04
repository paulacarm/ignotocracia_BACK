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

import com.ignotocracia.app.entity.TipoJuego;
import com.ignotocracia.app.service.TipoJuegoService;

@RestController
@RequestMapping("/api/tiposdejuego")
@CrossOrigin
public class TipoJuegoController {
	
	@Autowired
	private TipoJuegoService tipoJuegoService ;
	
	@CrossOrigin
	@GetMapping
	public List<TipoJuego> readAll(){
		 List<TipoJuego> tiposjuego= StreamSupport
				 .stream(tipoJuegoService.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return tiposjuego;
	}
	
	
	/**Crear tipo de juego nuevo
	 * Solo para el administrador
	 * @param cronologia
	 * @return ResponseEntity<Cronologia> . Estado creado
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TipoJuego> create(@RequestBody TipoJuego tipoJuego){
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoJuegoService.save(tipoJuego));
	}
	
	/**Consultar un logro
	 * Comprueba que id existe 
	 * @param tipoJuegoId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer tipoJuegoId){

		Optional<TipoJuego> tipoJuego = tipoJuegoService.findById(tipoJuegoId);
		
		if(!tipoJuego.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tipoJuego);
	}
	/**
	 * Editar tipoJuego
	 * @param tipoJuegoDetails
	 * @param tipoJuegoId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TipoJuego tipoJuegoDetails,@PathVariable(value="id") Integer tipoJuegoId){
		
		Optional<TipoJuego> tipoJuego=tipoJuegoService.findById(tipoJuegoId);
		
		if(!tipoJuego.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		tipoJuego.get().setNombre(tipoJuegoDetails.getNombre());


		return ResponseEntity.status(HttpStatus.CREATED).body(tipoJuegoService.save(tipoJuego.get()));
	}
	/**
	 * Borrar tipoJuego
	 * @param tipoJuegoId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Integer tipoJuegoId ){
		
		if(!tipoJuegoService.findById(tipoJuegoId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		tipoJuegoService.deleteById(tipoJuegoId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}


}
