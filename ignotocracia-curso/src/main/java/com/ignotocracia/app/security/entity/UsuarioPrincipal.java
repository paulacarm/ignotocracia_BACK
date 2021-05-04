package com.ignotocracia.app.security.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

/**Esta clase será la encargada de generar la seguridad
Tendrá los mismos atributos que la clase Usuario, excepuando el id.
En vez de tener roles tendrá authorities.(Clase propia de la seguridad de Spring boot).
Esta clase no es una entidad. No estará en la BD*/

public class UsuarioPrincipal implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nombreusuario;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UsuarioPrincipal(String nombre, String nombreusuario, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.nombre = nombre;
		this.nombreusuario = nombreusuario;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	//Convierte un Usuario que representa una entidad de la BD en un 
	//UsuarioPrincipal que utilizamos para ver sus privilegios
	public static UsuarioPrincipal build(Usuario usuario) {
	
		//Obteción de una lista de GrantedAuthority a partir de los roles
	
		 List<GrantedAuthority> authorities =
	                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
	                .getRolNombre().name())).collect(Collectors.toList());
		 
	        return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	@Override
	public String getPassword() {
	
		return password;
	}
	@Override
	public String getUsername() {
		
		return nombreusuario;
	}
	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
