package com.ignotocracia.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignotocracia.app.security.entity.Rol;
import com.ignotocracia.app.security.enums.RolNombre;



public interface RolRepository extends JpaRepository<Rol,Integer> {
	Optional<Rol>findByRolNombre(RolNombre rolNombre);
}
