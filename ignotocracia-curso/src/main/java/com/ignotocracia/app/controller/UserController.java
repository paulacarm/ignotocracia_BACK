package com.ignotocracia.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignotocracia.app.entity.User;
import com.ignotocracia.app.service.UserService;



@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//CRUD
	//Crear usuario
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user){
		//Va a recibir en el cuerpo de la peticion un usuario y que lo guardaremos y lo devolveremos
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	//Leer un usuario
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
		//controlar error si ID no existe
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}

	/**
	 * Editar usuario
	 * @param userDetails
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails,@PathVariable(value="id") Long userId){
		//Optional se utiliza para evitar los null en los objetos
		Optional<User> user=userService.findById(userId);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		//Otra manera de copiar el objeto entero. En este caso es el mismo id y no nos conviene
		//Clase BeanUtils.copyPropierties
		//Se pone el .get() para coger el objeto, si no es un optional
		//BeanUtils.copyProperties(userDetails, user.get());
		
		user.get().setName(userDetails.getName());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnabled(userDetails.getEnabled());
		user.get().setSurname(userDetails.getSurname());
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
	}
	
	//Borrar un usuario
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long userId ){
	Optional<User> user=userService.findById(userId);
		//Otra manera distinta de ver si el usuario existe
		if(!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(userId);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}

	
	//Leer todos los usuarios
	@CrossOrigin
	@GetMapping
	public List<User> readAll(){
		//Hay que convertir el iterable en una lista. false=secuencial en vez de paralelo
		//.stream(userService.findAll().spliterator(), false) para recorrer nuestro iterable
		// .collect(Collectors.toList()); lo pasa a lista
		 List<User> users= StreamSupport
				 .stream(userService.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return users;
	}
}
