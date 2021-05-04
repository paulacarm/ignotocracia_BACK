package com.ignotocracia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.TipoJuego;

@Repository
public interface TipoJuegoRepository extends JpaRepository<TipoJuego,Integer>{

}
