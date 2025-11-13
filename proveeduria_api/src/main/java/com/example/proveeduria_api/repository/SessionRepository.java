package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad SessionModel.
 * Permite realizar operaciones CRUD sobre la tabla de sesiones.
 */
public interface SessionRepository extends JpaRepository<SessionModel, Long> {
}
