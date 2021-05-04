package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.Tematica;

public interface TematicaService {
	
	public Iterable <Tematica> findAll();

	public Page<Tematica> findAll(Pageable pageable);
	
	public Optional<Tematica> findById(int id);
	
	public Tematica save(Tematica tematica);
	
	public void deleteById(int id);

}
