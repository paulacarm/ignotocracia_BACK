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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ignotocracia.app.entity.Pregunta;
import com.ignotocracia.app.entity.Respuesta;
import com.ignotocracia.app.service.RespuestaService;

/**
 * 
 * @author paula.carmona.moreno
 *
 */
@RestController
@RequestMapping("/api/respuestas")
@CrossOrigin(origins = "*")
public class RespuestaController {

	@Autowired
	private RespuestaService respuestaService;

	

	/**
	 * Crear respuesta
	 * @param respuesta
	 * @return estado creado
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Respuesta> create(@RequestBody Respuesta respuesta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaService.save(respuesta));
	}
	
	/**
	 * Listar todas las respuestas
	 * @return respuestas
	 */
	@CrossOrigin
	@GetMapping
	public List<Respuesta> readAll() {
		List<Respuesta> respuestas = StreamSupport.stream(respuestaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return respuestas;
	}
	
	/**
	 * Editar respuesta
	 * @param respuestaDetails
	 * @param respuestaId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Respuesta respuestaDetails,@PathVariable(value="id") Integer respuestaId){
		Optional<Respuesta> respuesta=respuestaService.findById(respuestaId);
		
		if(!respuesta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Pregunta p=respuesta.get().getPregunta();
		System.out.println("pregunta"+ p);
		respuesta.get().setEsVerdadera(respuestaDetails.isEsVerdadera());
		respuesta.get().setRespuesta(respuestaDetails.getRespuesta());
		respuesta.get().setPregunta(respuestaDetails.getPregunta());
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaService.save(respuesta.get()));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Integer respuestaId ){
		
		if(!respuestaService.findById(respuestaId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		respuestaService.deleteById(respuestaId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	/**Consultar una respuesta
	 * Comprueba que id existe 
	 * @param userId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer respuestaId){

		Optional<Respuesta> respuesta = respuestaService.findById(respuestaId);
		
		if(!respuesta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(respuesta);
	}
	/**
	 * Método personalizado que devuelve la respuesta verdadera de una pregunta
	 * @param preguntaId
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/respuestaVerdadera/{id}")
	public ResponseEntity<?>getRespuestaDePregunta(@PathVariable(value = "id") Integer preguntaId) {
		
		Optional<Respuesta> respuesta = respuestaService.gettRespuestaVerdaderaDePregunta(preguntaId);
		
		if(!respuesta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(respuesta);
	
		
	}
	
	@CrossOrigin
	@GetMapping("/pregunta/{id}")
	public List<Respuesta> getRespuestasDePregunta(@PathVariable(value = "id") Integer Id){
	
		List<Respuesta> respuestas= StreamSupport
				.stream(respuestaService.getRespuestasDePregunta(Id).spliterator(), false)
				.collect(Collectors.toList());
	
			 return respuestas;
		}
	
	

}
