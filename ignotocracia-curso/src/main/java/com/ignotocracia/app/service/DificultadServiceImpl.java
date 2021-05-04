package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.Dificultad;
import com.ignotocracia.app.repository.DificultadRepository;

@Service
public class DificultadServiceImpl implements DificultadService {
	
	@Autowired
	private DificultadRepository dr;
	
	@Override
	public Iterable<Dificultad> findAll() {
		
		return this.dr.findAll();
	}

	@Override
	public Page<Dificultad> findAll(Pageable pageable) {

		return this.dr.findAll(pageable);
	}

	@Override
	public Optional<Dificultad> findById(int id) {
	
		return this.dr.findById(id);
	}

}
