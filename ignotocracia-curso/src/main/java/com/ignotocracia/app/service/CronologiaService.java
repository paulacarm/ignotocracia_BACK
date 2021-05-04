package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.Cronologia;


public interface CronologiaService {
	
	public Iterable <Cronologia> findAll();

	public Page<Cronologia> findAll(Pageable pageable);
	
	public Optional<Cronologia> findById(int id);
	
	public Cronologia save(Cronologia cronologia);
	
	public void deleteById(int id);

}
