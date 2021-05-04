package com.ignotocracia.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ignotocracia.app.security.entity.Rol;
import com.ignotocracia.app.security.enums.RolNombre;
import com.ignotocracia.app.security.service.RolService;
/**
 * 
 * @author paula.carmona.moreno
 *Esta clase es para crear los dos roles.Solo se ejectutará una vez
 
@Component
public class CreateRoles implements CommandLineRunner{

	@Autowired
	RolService rolService;
	@Override
	public void run(String... args) throws Exception {
		Rol rolAdmin= new Rol(RolNombre.ROLE_ADMIN);
		Rol rolUser= new Rol(RolNombre.ROLE_USER);
		rolService.save(rolAdmin);
		rolService.save(rolUser);
		
	}

}*/
