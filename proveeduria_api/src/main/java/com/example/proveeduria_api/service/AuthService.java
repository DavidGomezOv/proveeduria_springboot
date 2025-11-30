/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.LoginResponseModel;
import com.example.proveeduria_api.models.SessionModel;
import com.example.proveeduria_api.models.UserModel;
import com.example.proveeduria_api.repository.SessionRepository;
import com.example.proveeduria_api.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

/**
 *
 * @author Megan
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
            SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // simple, para no añadir configuración extra
    }

    /**
     * Intenta autenticar con correo + contrasena. Verifica que exista sesión y
     * que esté "Activa".
     *
     * @param correo
     * @param rawPassword
     * @return AuthResponse con info del usuario si todo OK
     * @throws IllegalArgumentException si credenciales inválidas o usuario no
     * existe
     * @throws IllegalStateException si la sesión no existe o no está activa
     */
    public LoginResponseModel login(String correo, String rawPassword) {
        UserModel user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        // Verifica contrasena
        if (user.getContrasena() == null || !passwordEncoder.matches(rawPassword, user.getContrasena())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // Verifica sesión asociada
        Optional<SessionModel> optSession = sessionRepository.findByUserId(user.getId());
        if (optSession.isEmpty()) {
            throw new IllegalStateException("Sesión no encontrada para el usuario");
        }
        SessionModel session = optSession.get();
        if (!"Activa".equalsIgnoreCase(session.getSessionStatus())) {
            throw new IllegalStateException("La sesión del usuario no está activa");
        }

        String rolNombre = null;
        if (user.getRol() != null) {
            rolNombre = user.getRol().getNombreRol();
        }

        return new LoginResponseModel(user.getId(), user.getCedula(), user.getNombre(), user.getCorreo(), rolNombre);
    }

}
