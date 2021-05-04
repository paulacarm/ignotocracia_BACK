package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ignotocracia.app.entity.TipoJuego;

public interface TipoJuegoService {
	

	public Iterable <TipoJuego> findAll();
	
	public Page<TipoJuego> findAll(Pageable pageable);
	
	public Optional<TipoJuego> findById(Integer id);
	
	public TipoJuego save(TipoJuego tipoJuego);
	
	public void deleteById(Integer id);

}
