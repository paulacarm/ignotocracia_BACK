package com.ignotocracia.app.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pregunta")
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String pregunta;
	//mirar si puede ser nulo
	@Column(name = "saberMas")
	private String saberMas;
	
	@NotNull
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "enlaceArticulo")
	private String enlaceArticulo;
	//Many hace referencia a la entidad y One al atributo.Muchas preguntas pertenecen a un logro
	@ManyToOne
	@JoinColumn(name="logro_id")
	private Logro logro;
	
	@ManyToOne
	@JoinColumn(name="tipojuego_id")
	private TipoJuego tipoJuego ;
	
	@ManyToOne
	@JoinColumn(name="cronologia_id")
	private Cronologia cronologia ;

	@ManyToOne
	@JoinColumn(name="dificultad_id")
	private Dificultad dificultad ;
	
	/**
	 * Lista de respuestas de la pregunta. Interesa almacenarlas en una lista
	 */
	//@OneToMany(mappedBy="pregunta")
	//Set<Respuesta> listaRespuestas;
	
	public Pregunta() {
	
		
	}

	//TODO- MIRAR CONSTRUCTORES. HAY QUE AÑADIR TODOS LOS CAMPOS FK??.
	public Pregunta(@NotNull String pregunta, String saberMas, @NotNull String imagen, String enlaceArticulo,
			Logro logro, TipoJuego tipoJuego) {
		super();
		this.pregunta = pregunta;
		this.saberMas = saberMas;
		this.imagen = imagen;
		this.enlaceArticulo = enlaceArticulo;
		this.logro = logro;
		this.tipoJuego = tipoJuego;
	}


	public Pregunta(@NotNull String pregunta, String saberMas, @NotNull String imagen, String enlaceArticulo) {
		super();
		this.pregunta = pregunta;
		this.saberMas = saberMas;
		this.imagen = imagen;
		this.enlaceArticulo = enlaceArticulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getSaberMas() {
		return saberMas;
	}

	public void setSaberMas(String saberMas) {
		this.saberMas = saberMas;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEnlaceArticulo() {
		return enlaceArticulo;
	}

	public void setEnlaceArticulo(String enlaceArticulo) {
		this.enlaceArticulo = enlaceArticulo;
	}

	public Logro getLogro() {
		return logro;
	}

	public void setLogro(Logro logro) {
		this.logro = logro;
	}

	public TipoJuego getTipoJuego() {
		return tipoJuego;
	}

	public void setTipoJuego(TipoJuego tipoJuego) {
		this.tipoJuego = tipoJuego;
	}


	public Cronologia getCronologia() {
		return cronologia;
	}

	public void setCronologia(Cronologia cronologia) {
		this.cronologia = cronologia;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	


	
	
	
	

	
	

}
