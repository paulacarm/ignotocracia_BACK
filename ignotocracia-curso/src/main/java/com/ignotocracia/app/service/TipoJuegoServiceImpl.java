package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.TipoJuego;
import com.ignotocracia.app.repository.TipoJuegoRepository;

@Service
public class TipoJuegoServiceImpl implements TipoJuegoService {

	@Autowired
	private TipoJuegoRepository tprepository;

	@Override
	public Iterable<TipoJuego> findAll() {
		
		return this.tprepository.findAll();
	}

	@Override
	public Page<TipoJuego> findAll(Pageable pageable) {
		
		return  this.tprepository.findAll(pageable);
	}

	@Override
	public Optional<TipoJuego> findById(Integer id) {
		
		return this.tprepository.findById(id);
	}

	@Override
	public TipoJuego save(TipoJuego tipoJuego) {
		
		return this.tprepository.save(tipoJuego);
	}

	@Override
	public void deleteById(Integer id) {
	this.tprepository.deleteById(id);
		
	}
	



}
