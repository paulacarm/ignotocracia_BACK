package com.ignotocracia.app.security.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ignotocracia.app.entity.TipoJuego;
import com.ignotocracia.app.security.dto.JwtDto;
import com.ignotocracia.app.security.dto.LoginUsuario;
import com.ignotocracia.app.security.dto.Mensaje;
import com.ignotocracia.app.security.dto.NuevoUsuario;
import com.ignotocracia.app.security.entity.Rol;
import com.ignotocracia.app.security.entity.Usuario;
import com.ignotocracia.app.security.enums.RolNombre;
import com.ignotocracia.app.security.jwt.JwtProvider;
import com.ignotocracia.app.security.service.RolService;
import com.ignotocracia.app.security.service.UsuarioService;

/**
 * 
 * @author paula.carmona.moreno API REST.
 *
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;
	
	

	/**
	 * Espera un Json que convierte en NuevoUsuario
	 * 
	 * @param nuevoUsuario
	 * @param bindingResult
	 * @return estado OK.
	 */
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		// Primero se combrueban errores
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (usuarioService.existByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return new ResponseEntity(new Mensaje("Nombre en uso"), HttpStatus.BAD_REQUEST);
		if (usuarioService.existByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity(new Mensaje("Email en uso"), HttpStatus.BAD_REQUEST);
		// Si no hay errores se crea el usuario con su rol correspondiente y contraseña
		// cifrada
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	}

	/**
	 * Método login de la app. Capitulo 5
	 * 
	 * @param loginUsuario
	 * @param bindingResult
	 * @return estado OK.
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
	
	@GetMapping("/{usuario}")
	public ResponseEntity<?>getUsuarioXNombre(@PathVariable(value = "usuario") String nombreUsuario){

		Optional<Usuario> usuario=this.usuarioService.getByNombreUsuario(nombreUsuario);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

}
