package com.ignotocracia.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.security.entity.Usuario;

public interface UsuarioLogroService {

	public Iterable <UsuarioLogro> findAll();
	
	public Page<UsuarioLogro> findAll(Pageable pageable);
	
	public Optional<UsuarioLogro> findById(UsuarioLogroId id);
	
	
	public Optional<UsuarioLogro> findByUsuario(Usuario usuario);
	
	public UsuarioLogro save(UsuarioLogro usuarioLogro);
	
	public void deleteById(UsuarioLogroId id);
	
	public Optional<UsuarioLogro>getByNombreUsuario(String nombreUsuario);
	
	public List<UsuarioLogro> getLogrosDeUsuario(Integer id);
	
}

