package com.ignotocracia.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.ignotocracia.app.entity.Pregunta;


public interface PreguntaService {
	
	public Iterable <Pregunta> findAll();

	public Page<Pregunta> findAll(Pageable pageable);
	
	public Optional<Pregunta> findById(int id);
	
	public Pregunta save(Pregunta pregunta);
	
	public void deleteById(int id);
	
	List<Pregunta> getPreguntasPorTipoJuego(Integer id);

	List<Pregunta> getPreguntaspPorDicultad(Integer id);
	
	List<Pregunta> getPreguntaspPoLogro(Integer id);

}


