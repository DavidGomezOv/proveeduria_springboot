package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.UserModel;
import com.example.proveeduria_api.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de usuarios.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserService service;

    /**
     * Inyección del servicio de usuarios.
     * @param service servicio que maneja la lógica de negocio de usuarios.
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios.
     */
    @GetMapping
    public List<UserModel> getAll() {
        return service.getAll();
    }
}
