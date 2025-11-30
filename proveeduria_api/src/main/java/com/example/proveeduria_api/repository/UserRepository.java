package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad UserModel. Permite realizar operaciones CRUD
 * sobre la tabla de usuarios.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Busca usuario por correo.
     *
     * @param correo
     * @return UserModel
     */
    Optional<UserModel> findByCorreo(String correo);
}
