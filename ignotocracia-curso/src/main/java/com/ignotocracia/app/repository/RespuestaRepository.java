package com.ignotocracia.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Respuesta;


@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta,Integer> {
	/**
	 * 
	 * @param id
	 * @return respuestas de pregunta
	 */
	@Query(nativeQuery = true,value = " SELECT * FROM respuesta  WHERE pregunta_id =?")
	List<Respuesta> getRespuestasDePregunta(Integer id);
	/**
	 * 
	 * @param id
	 * @return respuesta verdadera de pregunta
	 */
	@Query(nativeQuery = true,value = "SELECT * from respuesta where pregunta_id=? and es_verdadera=true")
	Optional<Respuesta>getRespuestaVerdaderaDePregunta(Integer id);
	

}
