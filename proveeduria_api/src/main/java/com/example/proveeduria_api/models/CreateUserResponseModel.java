/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

public class CreateUserResponseModel {

    private Integer id;
    private Long cedula;
    private String nombre;
    private String correo;
    private String rol;
    private Integer idSesion;
    private String sessionStatus;

    public CreateUserResponseModel(Integer id, Long cedula, String nombre, String correo, String rol, Integer idSesion, String sessionStatus) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.idSesion = idSesion;
        this.sessionStatus = sessionStatus;
    }

    // getters
    public Integer getId() {
        return id;
    }

    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }
}
