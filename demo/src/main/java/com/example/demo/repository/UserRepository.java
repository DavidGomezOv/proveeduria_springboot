package com.example.demo.repository;

import com.example.demo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad UserModel.
 * Permite realizar operaciones CRUD sobre la tabla de usuarios.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
