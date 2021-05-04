package com.ignotocracia.app.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.ignotocracia.app.entity.UsuarioLogro;

import javax.persistence.JoinColumn;

//Esta clase se encarga del acceso a la Base de Datos.

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	@Column(unique=true)
	private String nombreUsuario;

	@NotNull
	private String email;
	@NotNull
	private String password;
	
	//Crear tabla intermedia para representar la relacion N:M
	//Un usuario puede tener varios roles y un rol pertenece a varios usuarios
	//La tabla intermedia tendrá 2 campos: El id del usuario y el ID del rol
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles= new HashSet<>();
	
	@OneToMany(mappedBy = "usuario")
	private Set<UsuarioLogro> logros = new HashSet<>();
	
	public Usuario() {
		
	}

	//Constructor que tendrá todos los atributos exceptuando el id y los roles
	public Usuario(String nombre, String nombreUsuario, String email, String password) {
		super();
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreusuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
	
	
	
}
