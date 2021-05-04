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
import com.ignotocracia.app.entity.Tematica;
import com.ignotocracia.app.service.CronologiaService;
import com.ignotocracia.app.service.TematicaService;

@RestController
@RequestMapping("/api/tematica")
@CrossOrigin
public class TematicaController {
	@Autowired
	private TematicaService tematicaService;

	/**
	 * Crear temática nueva 
	 * Solo para el administrador
	 * @param cronologia
	 * @return ResponseEntity<Cronologia> . Estado creado
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Tematica> create(@RequestBody Tematica tematica) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaService.save(tematica));

	}

	/**
	 * Consultar una tematica
	 * @param tematicaId
	 * @return Temática
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer tematicaId) {

		Optional<Tematica> t = tematicaService.findById(tematicaId);

		if (!t.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(t);
	}

	/**
	 * Editar tematica
	 * @param tematicaDetails
	 * @param tematicaId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cronologia tematicaDetails,
			@PathVariable(value = "id") Integer tematicaId) {

		Optional<Tematica> tematica = tematicaService.findById(tematicaId);

		if (!tematica.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		tematica.get().setNombre(tematicaDetails.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaService.save(tematica.get()));
	}

	/**
	 * Borrar Temática
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer tematicaId) {

		if (!tematicaService.findById(tematicaId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		tematicaService.deleteById(tematicaId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * Ver todas las cronologias
	 * 
	 * @return Lista cronologias
	 */
	@GetMapping
	public List<Tematica> readAll() {

		List<Tematica> tematicas = StreamSupport.stream(tematicaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return tematicas;
	}

}
