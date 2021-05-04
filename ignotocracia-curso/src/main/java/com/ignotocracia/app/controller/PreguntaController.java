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

import com.ignotocracia.app.entity.Pregunta;
import com.ignotocracia.app.entity.Respuesta;
import com.ignotocracia.app.entity.User;
import com.ignotocracia.app.service.PreguntaService;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin
public class PreguntaController {

	@Autowired
	private PreguntaService preguntaService;

	// CRUD
	// Crear pregunta
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Pregunta> create(@RequestBody Pregunta pregunta) {
		// Va a recibir en el cuerpo de la peticion un usuario y que lo guardaremos y lo
		// devolveremos
		return ResponseEntity.status(HttpStatus.CREATED).body(preguntaService.save(pregunta));
	}

	@CrossOrigin
	@GetMapping("/tipo/{id}")
	public List<Pregunta> readPorTipoJuego(@PathVariable(value = "id") Integer Id) {

		List<Pregunta> preguntas = StreamSupport
				.stream(preguntaService.getPreguntasPorTipoJuego(Id).spliterator(), false).collect(Collectors.toList());

		return preguntas;
	}
	
	@CrossOrigin
	@GetMapping("/dificultad/{id}")
	public List<Pregunta> readPorTipoDificultad(@PathVariable(value = "id") Integer Id) {
		List<Pregunta> preguntas = StreamSupport
				.stream(preguntaService.getPreguntaspPorDicultad(Id).spliterator(), false).collect(Collectors.toList());

		return preguntas;
	}
	
	@CrossOrigin
	@GetMapping("/logro/{id}")
	public List<Pregunta> readPorLogro(@PathVariable(value = "id") Integer Id) {

		List<Pregunta> preguntas = StreamSupport
				.stream(preguntaService.getPreguntaspPoLogro(Id).spliterator(), false).collect(Collectors.toList());

		return preguntas;
	}



	@CrossOrigin
	@GetMapping
	public List<Pregunta> readAll() {
		List<Pregunta> preguntas = StreamSupport.stream(preguntaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return preguntas;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer preguntaId) {

		Optional<Pregunta> pregunta = preguntaService.findById(preguntaId);

		if (!pregunta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pregunta);
	}

	/**
	 * Editar pregunta
	 * 
	 * @param preguntaDetails
	 * @param preguntaId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Pregunta preguntaDetails,
			@PathVariable(value = "id") Integer preguntaId) {
		Optional<Pregunta> pregunta = preguntaService.findById(preguntaId);

		if (!pregunta.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		pregunta.get().setCronologia(preguntaDetails.getCronologia());
		pregunta.get().setDificultad(preguntaDetails.getDificultad());
		pregunta.get().setEnlaceArticulo(preguntaDetails.getEnlaceArticulo());
		pregunta.get().setImagen(preguntaDetails.getImagen());
		pregunta.get().setLogro(preguntaDetails.getLogro());
		pregunta.get().setPregunta(preguntaDetails.getPregunta());
		pregunta.get().setPregunta(preguntaDetails.getPregunta());
		pregunta.get().setEnlaceArticulo(preguntaDetails.getEnlaceArticulo());
		pregunta.get().setTipoJuego(preguntaDetails.getTipoJuego());
		pregunta.get().setSaberMas(preguntaDetails.getSaberMas());

		return ResponseEntity.status(HttpStatus.CREATED).body(preguntaService.save(pregunta.get()));
	}

	/**
	 * Borrar pregunta
	 * 
	 * @param preguntaId
	 * @return OK
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer preguntaId) {
		Optional<Pregunta> user = preguntaService.findById(preguntaId);

		if (!preguntaService.findById(preguntaId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		preguntaService.deleteById(preguntaId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
