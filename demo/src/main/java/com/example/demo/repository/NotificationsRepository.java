/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.models.NotificationModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Megan
 */
public interface NotificationsRepository extends JpaRepository<NotificationModel, Long> {
    
    // Busca todas las notificaciones por id de usuario
    List<NotificationModel> findByIdUsuario(Long idUsuario);
}