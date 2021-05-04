package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.Logro;
import com.ignotocracia.app.repository.LogroRepository;


@Service
public class LogroSeriveImpl implements LogroService {
	
	@Autowired
	private LogroRepository logroRepository;

	@Override
	public Iterable<Logro> findAll() {
		
		return this.logroRepository.findAll();
	}

	@Override
	public Page<Logro> findAll(Pageable pageable) {
		
		return this.logroRepository.findAll(pageable);
	}

	@Override
	public Optional<Logro> findById(int id) {
	
		return this.logroRepository.findById(id);
	}

	@Override
	public Logro save(Logro logro) {

		return this.logroRepository.save(logro);
	}

	@Override
	public void deleteById(int id) {
		this.logroRepository.deleteById(id);
		
	}

}
