package com.example.proveeduria_api.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una notificación enviada a un usuario.
 * Mapea la tabla "Notificacion" de la base de datos.
 */
@Entity
@Table(name = "Notificacion", schema = "dbo")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer id;

    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_estadorevision")
    private Integer idEstadoRevision;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "mensaje")
    private String mensaje;

    /** ID de la notificación. */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** ID de la orden asociada a la notificación. */
    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    /** ID del usuario que recibe la notificación. */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** ID del estado de revisión asociado. */
    public Integer getIdEstadoRevision() {
        return idEstadoRevision;
    }

    public void setIdEstadoRevision(Integer idEstadoRevision) {
        this.idEstadoRevision = idEstadoRevision;
    }

    /** Fecha y hora en que se envió la notificación. */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /** Mensaje del contenido de la notificación. */
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
