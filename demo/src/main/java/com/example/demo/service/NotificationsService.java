package com.example.demo.service;

import com.example.demo.models.NotificationModel;
import com.example.demo.repository.NotificationsRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de negocio relacionada con las notificaciones.
 */
@Service
public class NotificationsService {
    
    private final NotificationsRepository repository;

    /**
     * Inyección del repositorio de notificaciones.
     * @param repository repositorio JPA de notificaciones
     */
    public NotificationsService(NotificationsRepository repository) {
        this.repository = repository;
    }
  
    /**
     * Obtiene todas las notificaciones asociadas a un usuario.
     * @param idUsuario ID del usuario
     * @return lista de notificaciones
     */
    public List<NotificationModel> getNotificationsPerUser(Long idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }
}
