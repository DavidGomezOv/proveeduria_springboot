package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.CreateUserRequestModel;
import com.example.proveeduria_api.models.CreateUserResponseModel;
import com.example.proveeduria_api.models.SessionModel;
import com.example.proveeduria_api.models.UserModel;
import com.example.proveeduria_api.service.SessionService;
import com.example.proveeduria_api.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de usuarios.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    /**
     * Inyección del servicio de usuarios.
     *
     * @param service servicio que maneja la lógica de negocio de usuarios.
     */
    public UserController(UserService service, SessionService sessionService) {
        this.userService = service;
        this.sessionService = sessionService;
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de usuarios.
     */
    @GetMapping
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    /**
     * Actualiza el estado de la sesión del usuario solo si la sesión existe.
     * Ej: PUT /api/users/1/session?estado=Activa
     *
     * @param idUsuario ID del usuario
     * @param estado nuevo estado de sesión
     * @return SessionModel actualizada o 404 si no existe sesión
     */
    @PutMapping("/{idUsuario}/session")
    public ResponseEntity<?> updateSessionStatus(@PathVariable Long idUsuario,
            @RequestParam("estado") String estado) {
        try {
            SessionModel updated = sessionService.updateSessionStatusIfExists(idUsuario, estado);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al actualizar sesión: " + ex.getMessage());
        }
    }

    /**
     * Crea un usuario y responde 201 Created con el usuario (sin contrasena).
     *
     * @param requestModel
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestModel requestModel) {
        try {
            CreateUserResponseModel resp = userService.createUser(requestModel);
            return ResponseEntity.status(201).body(resp);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al crear usuario: " + ex.getMessage());
        }
    }
}
