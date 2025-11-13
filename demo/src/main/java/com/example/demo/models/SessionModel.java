package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entidad que representa una sesión de usuario.
 * Mapea la tabla "Sesion" en la base de datos.
 */
@Entity
@Table(name = "Sesion", schema = "dbo")
public class SessionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Long id;

    @Column(name = "estado_sesion")
    private String sessionStatus;

    // Relación uno a uno con el usuario correspondiente
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private UserModel user;

    /** ID de la sesión. */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Estado actual de la sesión (Activa o Inactiva.). */
    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /** Usuario al que pertenece la sesión. */
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
