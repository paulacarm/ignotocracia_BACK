package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.Logro;


public interface LogroService {
	
	public Iterable <Logro> findAll();

	public Page<Logro> findAll(Pageable pageable);
	
	public Optional<Logro> findById(int id);
	
	public Logro save(Logro logro);
	
	public void deleteById(int id);

}
