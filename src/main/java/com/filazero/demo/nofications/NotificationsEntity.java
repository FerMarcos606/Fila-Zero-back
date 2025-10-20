package com.filazero.demo.nofications;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.delivery.DeliveryEntity;
import com.filazero.demo.typeNotifications.TypeNotificationsEntity;

@Entity
@Table(name = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id: FK al cliente receptor (CustomerEntity)
    // JPA necesita acceso a este campo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer; 

    // delivery_id: FK al pedido/entrega (DeliveryEntity). Opcional.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id") 
    private DeliveryEntity delivery; 

    // Fecha y hora
    @Builder.Default 
    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime = LocalDateTime.now(); 

    // type_notification_id: FK al tipo de notificación (TypeNotificationEntity)
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "type_notification_id", nullable = false)
    private TypeNotificationsEntity typeNotification;

    // Indica si ha sido leída
    @Builder.Default 
    @Column(name = "is_read", nullable = false)
    @ToString.Include
    private boolean isRead = false;

}