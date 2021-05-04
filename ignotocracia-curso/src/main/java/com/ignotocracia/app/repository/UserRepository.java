package com.ignotocracia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignotocracia.app.entity.User;
//Se pone la entidad y el tipo del id
//Si necesitamos un crud cimple podemos usar CRUDREPOSITORY 
//pero si vamos a usar paginacion del lado del servidor cuando trabajamos con muchos datos
//Nos viene con metodos que devuelven las entidades ordenadas y encuentra por paginas
//JPA REPOSITORY implementa la paginación
//NO HACE FALTA PONER MÁS NADA. 
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
