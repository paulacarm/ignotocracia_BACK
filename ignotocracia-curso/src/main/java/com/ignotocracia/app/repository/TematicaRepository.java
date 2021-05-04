package com.ignotocracia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Tematica;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica,Integer> {

}
