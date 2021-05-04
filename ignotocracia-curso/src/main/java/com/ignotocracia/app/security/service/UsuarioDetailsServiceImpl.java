package com.ignotocracia.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ignotocracia.app.security.entity.Usuario;
import com.ignotocracia.app.security.entity.UsuarioPrincipal;

//Servicio de Usuario principal(que no será una clase en la BD)

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UsuarioService usuarioService;
	
	/**
	 * Convierte UsuarioPrincipal en usuario
	 */
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario= usuarioService.getByNombreUsuario(nombreUsuario).get();
		return UsuarioPrincipal.build(usuario);
	}
	
	

}
