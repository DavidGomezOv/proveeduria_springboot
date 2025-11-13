package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.RolModel;
import com.example.proveeduria_api.service.RolService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los roles del sistema.
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

    private final RolService service;

    /**
     * Inyección del servicio de roles.
     * @param service servicio que maneja la lógica de negocio de roles.
     */
    public RolController(RolService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los roles registrados.
     * @return lista de roles.
     */
    @GetMapping
    public List<RolModel> getAll() {
        return service.getAll();
    }
}
