/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Megan
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")


public class NotificationsController {
    private final NotificationsService service;

    public NotificationsController(NotificationsService service) {
        this.service = service;
    }
    
     @GetMapping("/usuario/{idUsuario}")
    public List<NotificationModel> obtenerNotificacionesPorUsuario(@PathVariable Long idUsuario) {
        return service.getNotificationsPerUser(idUsuario);
    }
}
