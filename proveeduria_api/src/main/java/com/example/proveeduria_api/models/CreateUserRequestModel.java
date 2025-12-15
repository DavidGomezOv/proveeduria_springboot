/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

/**
 *
 * @author Megan
 */
public class CreateUserRequestModel {

    private Long cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private Long rolId;
    private Long financialRangeId;

    // getters y setters
    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public Long getFinancialRangeId() {
        return financialRangeId;
    }

    public void setFinancialRangeId(Long financialRangeId) {
        this.financialRangeId = financialRangeId;
    }

}
