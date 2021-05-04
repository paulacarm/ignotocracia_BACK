package com.ignotocracia.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.Respuesta;


public interface RespuestaService  {
	

	public Iterable <Respuesta> findAll();

	public Page<Respuesta> findAll(Pageable pageable);
	
	public Optional<Respuesta> findById(Integer id);
	
	public Respuesta save(Respuesta respuesta);
	
	public void deleteById(Integer id);
	
	public List<Respuesta> getRespuestasDePregunta(Integer id);
	
	public Optional<Respuesta>gettRespuestaVerdaderaDePregunta(Integer id);
	

}
