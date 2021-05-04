package com.ignotocracia.app.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Logro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id_logro;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	//Cada vez que se carque el logro se cargaran las preguntas. Si hay muchas realentiza la app
	//Se puede recuperar de manera manual la lista de preguntas en caso de ser muchas
	//mappedBy:nombre del atributo en la otra clase(Logro logro)
	
	//@OneToMany(mappedBy="logro")
	//Set<Pregunta> listaPreguntas;
	
	@OneToMany(mappedBy = "logro")
	private Set<UsuarioLogro> usuarios = new HashSet<>();
	
	public int getId_logro() {
		return id_logro;
	}

	public void setId_logro(int id_logro) {
		this.id_logro = id_logro;
	}

	public Logro() {
		
	}

	public Logro(String nombre) {
		super();
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
	

}
