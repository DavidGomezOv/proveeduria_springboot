package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.SessionModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad SessionModel. Permite realizar operaciones
 * CRUD sobre la tabla de sesiones.
 */
public interface SessionRepository extends JpaRepository<SessionModel, Long> {

    /**
     * Busca la sesión por el id del usuario (foreign key en SessionModel.user).
     *
     * @param userId id del usuario
     * @return Optional con la sesión si existe
     */
    Optional<SessionModel> findByUserId(Long userId);
}
