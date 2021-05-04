package com.ignotocracia.app.security.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignotocracia.app.security.entity.Usuario;
import com.ignotocracia.app.security.repository.UsuarioRepository;
// Transactional es para mantener la coherencia en la BD. Si falla una operacion se hace un rollback
/**
 * 
 * @author paula.carmona.moreno
 *
 */

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	//Aqui el nombre del método da igual. Donde tiene que estar de una forma determinada es en el repositorio
	public Optional<Usuario>getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}
