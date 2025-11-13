package com.example.demo.service;

import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios.
 */
@Service
public class UserService {
    
    private final UserRepository repository;

    /**
     * Inyección del repositorio de usuarios.
     * @param repository repositorio JPA de usuarios
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios
     */
    public List<UserModel> getAll() {
        return repository.findAll();
    }
}
