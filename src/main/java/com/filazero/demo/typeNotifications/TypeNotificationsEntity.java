package com.filazero.demo.typeNotifications;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_notifications")
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @Getter y @Setter eliminados intencionalmente si son manejados por DTOs
@ToString(onlyExplicitlyIncluded = true)

public class TypeNotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = true)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private String status; // Ej: "pendiente", "asignado", "completado"

    @Column(nullable = true)
    private LocalDateTime deadline;
}
