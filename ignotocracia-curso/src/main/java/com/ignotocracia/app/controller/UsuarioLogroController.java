package com.ignotocracia.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ignotocracia.app.entity.Logro;
import com.ignotocracia.app.entity.TipoJuego;
import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.security.entity.Usuario;
import com.ignotocracia.app.security.service.UsuarioService;
import com.ignotocracia.app.service.UsuarioLogroService;
/**
 * 
 * @author paula.carmona.moreno
 *
 */
@RestController
@RequestMapping("/api/logrosusuario")
@CrossOrigin(origins ="*")
public class UsuarioLogroController {

	@Autowired
	UsuarioLogroService ulservice;
	

	@Autowired
	UsuarioService uservice;
	

	@PostMapping("/insertar")
	public ResponseEntity<UsuarioLogro> create(@RequestBody UsuarioLogro ul){
		return ResponseEntity.status(HttpStatus.CREATED).body(ulservice.save(ul));
	}
	
	
	@CrossOrigin
	@GetMapping
	public List<UsuarioLogro> readAll(){
		 List<UsuarioLogro> logrosusuario= StreamSupport
				 .stream(ulservice.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 return logrosusuario;
	}
	@GetMapping("/{idUsuario}")
	public List<UsuarioLogro> readLogroPorUsuario(@PathVariable(value = "idUsuario")  int idUsuario){
		 List<UsuarioLogro> logros= StreamSupport
				 .stream(ulservice.getLogrosDeUsuario(idUsuario).spliterator(), false)
				 .collect(Collectors.toList());
		 return logros;
	}
	

	/**Consultar un logro
	 * Comprueba que id existe 
	 * @param logroId
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/{id1}/{id2}")
	public ResponseEntity<?> read(@PathVariable("id1") Integer id1,@PathVariable("id2") Integer id2 ){
		UsuarioLogroId id=new UsuarioLogroId(id1,id2);
		Optional<UsuarioLogro> ul = ulservice.findById(id);
		
		if(!ul.isPresent()) {
			System.out.println("no encontrado");
			return ResponseEntity.notFound().build();
	
		}
		return ResponseEntity.ok(ul);
	}
	
	/**
	 * Editar logro
	 * @param logroDetails
	 * @param logroId
	 * @return
	 */
	@PutMapping("/{id1}/{id2}")
	public ResponseEntity<?> update(@RequestBody UsuarioLogro uldetails,@PathVariable("id1") Integer id1,@PathVariable("id2") Integer id2){
		
		UsuarioLogroId id =new UsuarioLogroId(id1,id2);
		Optional<UsuarioLogro> ul=ulservice.findById(id);
		System.out.println(ul.get().getLogro());
		if(!ul.isPresent()) {
			return ResponseEntity.notFound().build();
	
		}
	
		int puntos= ul.get().getPuntos()+uldetails.getPuntos();
		System.out.println(puntos);
		ul.get().setPuntos(puntos);
		return ResponseEntity.status(HttpStatus.OK).body(ulservice.save(ul.get()));
	}
	
	
	/**
	 * Borrar logro
	 * @param logroId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id1}/{id2}")
	public ResponseEntity<?> delete(@PathVariable("id1") Integer id1,@PathVariable("id2") Integer id2){
		System.out.println(id1);
		System.out.println(id2);
		UsuarioLogroId id=new UsuarioLogroId(id1,id2);
		if(!ulservice.findById(id).isPresent()) {
			System.out.println("no encontrado");
			return ResponseEntity.notFound().build();
		}
		ulservice.deleteById(id);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	



}
