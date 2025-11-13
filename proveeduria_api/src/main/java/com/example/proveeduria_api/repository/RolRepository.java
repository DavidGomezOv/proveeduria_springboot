package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad RolModel.
 * Permite realizar operaciones CRUD sobre la tabla de roles.
 */
public interface RolRepository extends JpaRepository<RolModel, Long> {
}
