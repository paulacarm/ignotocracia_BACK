package com.ignotocracia.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Respuesta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "respuesta")
	private String respuesta;
	
	@NotNull
	@Column(name = "esVerdadera")
	private boolean esVerdadera;
	
	public boolean isEsVerdadera() {
		return esVerdadera;
	}


	public void setEsVerdadera(boolean esVerdadera) {
		this.esVerdadera = esVerdadera;
	}


	@ManyToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	
	
	public Respuesta() {
		
	}


	public Respuesta(@NotNull String respuesta, Pregunta pregunta) {
		super();
		this.respuesta = respuesta;
		this.pregunta = pregunta;
	}

	public Respuesta(@NotNull String respuesta) {
		super();
		this.respuesta = respuesta;
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public Pregunta getPregunta() {
		return pregunta;
	}


	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
	
	
}
