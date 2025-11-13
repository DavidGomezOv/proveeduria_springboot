package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.NotificationModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad NotificationModel.
 * Permite realizar operaciones CRUD sobre la tabla de notificaciones.
 */
public interface NotificationsRepository extends JpaRepository<NotificationModel, Long> {
    
    /**
     * Busca todas las notificaciones asociadas a un usuario.
     * @param idUsuario ID del usuario.
     * @return lista de notificaciones del usuario.
     */
    List<NotificationModel> findByIdUsuario(Long idUsuario);
}
