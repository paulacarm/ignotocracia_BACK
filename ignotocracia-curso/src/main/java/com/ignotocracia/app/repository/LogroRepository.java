package com.ignotocracia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Logro;

@Repository
public interface LogroRepository extends JpaRepository<Logro,Integer>{

}
