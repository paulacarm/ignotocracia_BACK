package com.ignotocracia.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ignotocracia.app.entity.User;

public interface UserService {
	
	//Devuelve un iterable. Este método  está en el JpaRepository
	public Iterable <User> findAll();
	//Devuele los usuarios con paginación
	public Page<User> findAll(Pageable pageable);
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
}
