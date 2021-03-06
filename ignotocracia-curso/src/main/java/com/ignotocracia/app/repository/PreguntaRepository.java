package com.ignotocracia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Pregunta;


@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta,Integer> {
	/**
	 * 
	 * @param id
	 * @return preguntas de tipo de juego por id
	 */
	@Query(nativeQuery = true,value = " SELECT * FROM pregunta  WHERE tipojuego_id =?")
	List<Pregunta> getPreguntaspPorTipoJuego(Integer id);
	/**
	 * 
	 * @param id
	 * @return preguntas por dificultad
	 */
	@Query(nativeQuery = true,value = " SELECT * FROM pregunta  WHERE dificultad_id =?")
	List<Pregunta> getPreguntaspPorDicultad(Integer id);
	/**
	 * 
	 * @param id
	 * @return preguntas por logro
	 */
	@Query(nativeQuery = true,value = " SELECT * FROM pregunta  WHERE logro_id=?")
	List<Pregunta> getPreguntaspPoLogro(Integer id);
	

}
