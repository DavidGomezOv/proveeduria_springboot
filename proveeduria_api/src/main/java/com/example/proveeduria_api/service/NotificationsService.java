package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.NotificationModel;
import com.example.proveeduria_api.models.NotificationResponseModel;
import com.example.proveeduria_api.repository.NotificationsRepository;
import java.util.ArrayList;
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
    public List<NotificationResponseModel> getNotificationsPerUser(Integer idUsuario) {
        final List<NotificationModel> rawNotifications = repository.findByUser_Id(idUsuario);
        
        List<NotificationResponseModel> notifications = new ArrayList<>();
        
        for (var notification : rawNotifications) {
            NotificationResponseModel notificationResponseModel = new NotificationResponseModel();
            notificationResponseModel.setId(notification.getId());
            notificationResponseModel.setMensaje(notification.getMensaje());
            notificationResponseModel.setOrderId(notification.getOrder().getId());
            notificationResponseModel.setSentDate(notification.getSentDate());
            
            notifications.add(notificationResponseModel);
        }
        
        return notifications;
    }
}
