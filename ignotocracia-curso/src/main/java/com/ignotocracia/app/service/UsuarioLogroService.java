package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;

public interface UsuarioLogroService {

	public Iterable <UsuarioLogro> findAll();
	
	public Page<UsuarioLogro> findAll(Pageable pageable);
	
	public Optional<UsuarioLogro> findById(UsuarioLogroId id);
	
	public UsuarioLogro save(UsuarioLogro usuarioLogro);
	
	public void deleteById(UsuarioLogroId id);
}
