package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema.
 * Mapea la tabla "Usuario" en la base de datos.
 */
@Entity
@Table(name = "Usuario", schema = "dbo")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "cedula")
    private Long cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    // Relación uno a uno con la sesión del usuario
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private SessionModel sesion;
    
    // Relación muchos a uno con el rol asignado al usuario
    @ManyToOne
    @JoinColumn(name = "id_rol")
    @JsonManagedReference
    private RolModel rol;
    
    /** ID del usuario. */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Cédula o identificación del usuario. */
    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    /** Nombre completo del usuario. */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Correo electrónico del usuario. */
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /** Sesión actual del usuario. */
    public SessionModel getSesion() {
        return sesion;
    }

    public void setSesion(SessionModel sesion) {
        this.sesion = sesion;
    }

    /** Rol asignado al usuario. */
    public RolModel getRol() {
        return rol;
    }

    public void setRol(RolModel rol) {
        this.rol = rol;
    }
}
