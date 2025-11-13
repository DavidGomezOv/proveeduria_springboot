package com.example.demo.controller;

import com.example.demo.models.NotificationModel;
import com.example.demo.service.NotificationsService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las notificaciones de los usuarios.
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationsController {

    private final NotificationsService service;

    /**
     * Inyección del servicio de notificaciones.
     * @param service servicio que maneja la lógica de notificaciones.
     */
    public NotificationsController(NotificationsService service) {
        this.service = service;
    }

    /**
     * Obtiene las notificaciones de un usuario por su ID.
     * @param idUsuario ID del usuario.
     * @return lista de notificaciones del usuario.
     */
    @GetMapping("/usuario/{idUsuario}")
    public List<NotificationModel> obtenerNotificacionesPorUsuario(@PathVariable Long idUsuario) {
        return service.getNotificationsPerUser(idUsuario);
    }
}
