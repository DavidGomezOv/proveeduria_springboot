/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.SessionModel;
import com.example.proveeduria_api.repository.SessionRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para manejar sesiones de usuario.
 */
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Actualiza el sessionStatus de la sesión asociada a un usuario. Si la
     * sesión no existe, lanza IllegalStateException.
     *
     * @param userId ID del usuario
     * @param newStatus nuevo estado de sesión (ej. "Activa", "Inactiva")
     * @return SessionModel actualizada
     * @throws IllegalStateException si no existe sesión para el usuario
     */
    @Transactional
    public SessionModel updateSessionStatusIfExists(Long userId, String newStatus) {
        Optional<SessionModel> optSession = sessionRepository.findByUserId(userId);

        SessionModel session = optSession.orElseThrow(
                () -> new IllegalStateException("Sesión no encontrada para el usuario: " + userId)
        );

        session.setSessionStatus(newStatus);
        return sessionRepository.save(session);
    }
}
