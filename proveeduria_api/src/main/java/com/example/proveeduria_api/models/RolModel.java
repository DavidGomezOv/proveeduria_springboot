package com.example.proveeduria_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa un rol dentro del sistema.
 * Mapea la tabla "Rol" en la base de datos.
 */
@Entity
@Table(name = "Rol", schema = "dbo")
public class RolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre_rol")
    private String nombre;

    // Relación uno a muchos con usuarios que tienen este rol
    @OneToMany(mappedBy = "rol")
    @JsonBackReference
    private List<UserModel> usuarios;

    /** ID del rol. */
    public Long getIdRol() {
        return id;
    }

    public void setIdRol(Long id) {
        this.id = id;
    }

    /** Nombre del rol. */
    public String getNombreRol() {
        return nombre;
    }

    public void setNombreRol(String nombre) {
        this.nombre = nombre;
    }

    /** Lista de usuarios que pertenecen a este rol. */
    public List<UserModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserModel> usuarios) {
        this.usuarios = usuarios;
    }
}
