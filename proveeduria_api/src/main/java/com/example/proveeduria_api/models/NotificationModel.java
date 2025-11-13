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
    private Long id;

    @Column(name = "id_orden")
    private Long idOrden;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_estadorevision")
    private Long idEstadoRevision;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "mensaje")
    private String mensaje;

    /** ID de la notificación. */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** ID de la orden asociada a la notificación. */
    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    /** ID del usuario que recibe la notificación. */
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** ID del estado de revisión asociado. */
    public Long getIdEstadoRevision() {
        return idEstadoRevision;
    }

    public void setIdEstadoRevision(Long idEstadoRevision) {
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
