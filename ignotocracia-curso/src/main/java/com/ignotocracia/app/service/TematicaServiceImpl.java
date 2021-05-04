package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.Tematica;
import com.ignotocracia.app.repository.TematicaRepository;

@Service
public class TematicaServiceImpl implements TematicaService{
	@Autowired
	private TematicaRepository tr;
	
	@Override
	public Iterable<Tematica> findAll() {
		return tr.findAll();
	}

	@Override
	public Page<Tematica> findAll(Pageable pageable) {
		return tr.findAll(pageable);
	}

	@Override
	public Optional<Tematica> findById(int id) {
	
		return tr.findById(id);
	}

	@Override
	public Tematica save(Tematica tematica) {
	
		return tr.save(tematica);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
