package com.ignotocracia.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.repository.UsuarioLogroRepository;
import com.ignotocracia.app.security.entity.Usuario;

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



	@Override
	public Optional<UsuarioLogro> getByNombreUsuario(String nombreUsuario) {
	
		return this.ulrepository.findByUsuarioNombre(nombreUsuario);
	}

	@Override
	public Optional<UsuarioLogro>findByUsuario(Usuario usuario) {

		return this.findByUsuario(usuario);
	}



	@Override
	public List<UsuarioLogro> getLogrosDeUsuario(Integer id) {
		
		return this.ulrepository.getLogrosDeUsuario(id);
	}



}
