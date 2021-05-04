package com.ignotocracia.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.security.entity.Usuario;
@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Integer>{

	//Tener un usuario a partir del nombre de usuario
	//NombreUsuario es unico
	Optional<Usuario>findByNombreUsuario(String nombreUsuario);
	//El nombre de este metodo tiene que coincidir con el nombre del campo. Loquesea+nombredelcampo
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);
	
}
