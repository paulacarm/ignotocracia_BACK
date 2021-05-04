package com.ignotocracia.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignotocracia.app.entity.Dificultad;

import com.ignotocracia.app.service.DificultadService;

@RestController
@RequestMapping("/api/dificultad")
@CrossOrigin
public class DificultadController {
	
	@Autowired
	private DificultadService ds;
	
	/**
	 * 
	 * @param dificultadId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Integer dificultadId){
		
		Optional<Dificultad> dificultad = ds.findById(dificultadId);
		
		if(!dificultad.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dificultad);
	}
	
	@CrossOrigin
	@GetMapping
	public List<Dificultad> readAll(){
		//Hay que convertir el iterable en una lista. false=secuencial en vez de paralelo
		//.stream(userService.findAll().spliterator(), false) para recorrer nuestro iterable
		// .collect(Collectors.toList()); lo pasa a lista
		 List<Dificultad> dificultades= StreamSupport
				 .stream(ds.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return dificultades;
	}



}
