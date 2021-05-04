package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.Cronologia;
import com.ignotocracia.app.repository.CronologiaRepository;
@Service
public class CronologiaServiceImpl implements CronologiaService{
	@Autowired
	private CronologiaRepository cr;

	@Override
	public Iterable<Cronologia> findAll() {
		
		return this.cr.findAll();
	}

	@Override
	public Page<Cronologia> findAll(Pageable pageable) {
		
		return this.cr.findAll(pageable);
	}

	@Override
	public Optional<Cronologia> findById(int id) {
		
		return this.cr.findById(id);
	}

	@Override
	public Cronologia save(Cronologia cronologia) {
		return this.cr.save(cronologia);
	}

	@Override
	public void deleteById(int id) {
		this.cr.deleteById(id);
		
	}

}
