package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.RolModel;
import com.example.proveeduria_api.repository.RolRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de negocio relacionada con los roles.
 */
@Service
public class RolService {

    private final RolRepository repository;

    /**
     * Inyección del repositorio de roles.
     * @param repository repositorio JPA de roles
     */
    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los roles registrados.
     * @return lista de roles
     */
    public List<RolModel> getAll() {
        return repository.findAll();
    }
}
