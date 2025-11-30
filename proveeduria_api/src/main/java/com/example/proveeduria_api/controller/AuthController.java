/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.LoginRequestModel;
import com.example.proveeduria_api.models.LoginResponseModel;
import com.example.proveeduria_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador simple de autenticación (sin JWT).
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login: valida credenciales y estado de sesión.
     *
     * @param req { "correo": "...", "contrasena": "..." }
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestModel req) {
        try {
            LoginResponseModel resp = authService.login(req.getCorreo(), req.getContrasena());
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException ex) {
            // credenciales inválidas
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (IllegalStateException ex) {
            // sesión no existe o no está activa
            return ResponseEntity.status(403).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error en autenticación: " + ex.getMessage());
        }
    }

}
