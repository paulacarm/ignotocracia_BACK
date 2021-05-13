package com.ignotocracia.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Pregunta;
import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
import com.ignotocracia.app.security.entity.Usuario;
@Repository
public interface UsuarioLogroRepository extends JpaRepository<UsuarioLogro,UsuarioLogroId> {
	 //List<UsuarioLogro> findByIdLogroAndIdUsuario(int id_logro, int id_usuario);
	Optional<UsuarioLogro> findByUsuario(Usuario usuario);
	
	Optional<UsuarioLogro>findByUsuarioNombre(String nombreUsuario);
	
	@Query(nativeQuery = true,value = "select * from usuario_logro where usuario_id=? order by puntos desc")
	List<UsuarioLogro> getLogrosDeUsuario(Integer id);
}
