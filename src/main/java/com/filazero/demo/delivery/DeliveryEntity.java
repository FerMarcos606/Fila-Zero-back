package com.filazero.demo.delivery;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.detailDelivery.DetailDeliveryEntity;
import com.filazero.demo.nofications.NotificationsEntity;
import com.filazero.demo.turns.TurnsEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveries")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el cliente
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    // Relación 1:1 con el turno asignado
    @OneToOne
    @JoinColumn(name = "turn_id", nullable = false)
    private TurnsEntity turn;

    // Relación con los detalles del pedido
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DetailDeliveryEntity> details;

    // Estado del pedido (pendiente, listo, retirado, cancelado)
    private String status;

    // Fecha de creación del pedido
    private LocalDateTime createdAt;

    // Código de confirmación (opcional)
    private String confirmationCode;

    // Relación con notificaciones (opcional)
    @OneToMany(mappedBy = "delivery")
    private List<NotificationsEntity> notifications;
}


