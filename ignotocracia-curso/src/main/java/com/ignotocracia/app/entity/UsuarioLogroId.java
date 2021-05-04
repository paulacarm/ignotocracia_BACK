package com.ignotocracia.app.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
@Embeddable
public class UsuarioLogroId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id_usuario;

	private Integer id_logro;
	
	public UsuarioLogroId() {
		
	}

	public UsuarioLogroId(int id_usuario, int id_logro) {
		super();
		this.id_usuario = id_usuario;
		this.id_logro = id_logro;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_logro() {
		return id_logro;
	}

	public void setId_logro(int id_logro) {
		this.id_logro = id_logro;
	}

	
   @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
        result = prime * result + ((id_logro == null) ? 0 : id_logro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioLogroId other = ( UsuarioLogroId) obj;
        if (id_usuario == null) {
            if (other.id_usuario != null)
                return false;
        } else if (!id_usuario.equals(other.id_usuario))
            return false;
        if (id_logro == null) {
            if (other.id_logro!= null)
                return false;
        } else if (!id_logro.equals(other.id_logro))
            return false;
        return true;
    }
	

}
