package com.example.proveeduria_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una notificación enviada a un usuario. Mapea la tabla
 * "Notificacion" de la base de datos.
 */
@Entity
@Table(name = "Notificacion", schema = "dbo")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private UserModel user;

    @OneToOne
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    @JsonBackReference
    private OrderModel order;

    @OneToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @JsonBackReference
    private StatusModel status;

    @Column(name = "fecha_envio", insertable = false, updatable = false)
    private LocalDateTime sentDate;

    @Column(name = "mensaje")
    private String mensaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
