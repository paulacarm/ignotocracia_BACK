package com.ignotocracia.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;

import com.ignotocracia.app.security.entity.Usuario;

/**
 * 
 * @author paula.carmona.moreno
 *Esta clase representara la tabla N:M entre usuario y logro,ya que debemos almacenar el atributo extra puntos
 *Estará compuesta de los ids de ambas clases y del atributo puntos.
 */

@Entity
public class UsuarioLogro  implements Serializable{
private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UsuarioLogroId id=new UsuarioLogroId();
	
	@ManyToOne
	@MapsId("id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@MapsId("id_logro")
	private Logro logro;
	
	
	/*@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_logro")
	private Logro logro;*/
	
	@Column(name="puntos")
	private int puntos;



	public UsuarioLogro() {
		
	}
	
	/*public UsuarioLogro(Usuario usuario, Logro logro, int puntos) {
		super();
		this.id=new UsuarioLogroId(usuario.getId(),logro.getId());
		this.usuario = usuario;
		this.logro = logro;
		this.puntos = puntos;
	}*/







	public int getPuntos() {
		return puntos;
	}




	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Logro getLogro() {
		return logro;
	}

	public void setLogro(Logro logro) {
		this.logro = logro;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	
	
}
