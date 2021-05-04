package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.repository.UsuarioLogroRepository;

@Service
public class UsuarioLogroImpl implements UsuarioLogroService {
	@Autowired
	private UsuarioLogroRepository ulrepository;
	@Override
	public Iterable<UsuarioLogro> findAll() {

		return this.ulrepository.findAll();
	}

	@Override
	public Page<UsuarioLogro> findAll(Pageable pageable) {
	
		return this.ulrepository.findAll(pageable);
	}

	@Override
	public Optional<UsuarioLogro> findById(UsuarioLogroId id) {
		
		return this.ulrepository.findById(id);
	}

	@Override
	public UsuarioLogro save(UsuarioLogro usuarioLogro) {
		
		return this.ulrepository.save(usuarioLogro);
	}

	@Override
	public void deleteById(UsuarioLogroId id) {
		this.ulrepository.deleteById(id);
		
	}

}
