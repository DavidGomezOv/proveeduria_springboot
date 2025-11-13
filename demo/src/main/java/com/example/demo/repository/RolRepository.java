package com.example.demo.repository;

import com.example.demo.models.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad RolModel.
 * Permite realizar operaciones CRUD sobre la tabla de roles.
 */
public interface RolRepository extends JpaRepository<RolModel, Long> {
}
