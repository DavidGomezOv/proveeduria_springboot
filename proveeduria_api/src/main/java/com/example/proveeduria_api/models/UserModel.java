package com.example.proveeduria_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema. Mapea la tabla "Usuario" en la
 * base de datos.
 */
@Entity
@Table(name = "Usuario", schema = "dbo")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "cedula")
    private Long cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "correo")
    private String correo;

    // Relación uno a uno con la sesión del usuario
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private SessionModel sesion;

    // Relación muchos a uno con el rol asignado al usuario
    @ManyToOne
    @JoinColumn(name = "id_rol")
    @JsonManagedReference
    private RolModel rol;

    @ManyToOne
    @JoinColumn(name = "id_rango")
    @JsonManagedReference
    private FinancialRangeModel rango;

    @Column(name = "contrasena")
    private String contrasena;

    /**
     * ID del usuario.
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Cédula o identificación del usuario.
     */
    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    /**
     * Nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Sesión actual del usuario.
     */
    public SessionModel getSesion() {
        return sesion;
    }

    public void setSesion(SessionModel sesion) {
        this.sesion = sesion;
    }

    /**
     * Rol asignado al usuario.
     */
    public RolModel getRol() {
        return rol;
    }

    public void setRol(RolModel rol) {
        this.rol = rol;
    }

    public FinancialRangeModel getRango() {
        return rango;
    }

    public void setRango(FinancialRangeModel rango) {
        this.rango = rango;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
