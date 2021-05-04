package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.Dificultad;


public interface DificultadService {
	
	public Iterable <Dificultad> findAll();

	public Page<Dificultad> findAll(Pageable pageable);
	
	public Optional<Dificultad> findById(int id);
	


}
