/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.models.NotificationModel;
import com.example.demo.repository.NotificationsRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mergan
 */
@Service
public class NotificationsService {
    
    private final NotificationsRepository repository;

    public NotificationsService(NotificationsRepository repository) {
        this.repository = repository;
    }
  
    public List<NotificationModel> getNotificationsPerUser (Long idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }
}
