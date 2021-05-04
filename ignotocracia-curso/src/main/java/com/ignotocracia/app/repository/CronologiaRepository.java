package com.ignotocracia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.Cronologia;

@Repository
public interface CronologiaRepository extends JpaRepository<Cronologia,Integer> {

}
