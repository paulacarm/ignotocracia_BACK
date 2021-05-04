package com.ignotocracia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ignotocracia.app.entity.UsuarioLogro;
import com.ignotocracia.app.entity.UsuarioLogroId;
@Repository
public interface UsuarioLogroRepository extends JpaRepository<UsuarioLogro,UsuarioLogroId> {
	 //List<UsuarioLogro> findByIdLogroAndIdUsuario(int id_logro, int id_usuario);
}
