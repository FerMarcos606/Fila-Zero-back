package com.filazero.demo.typeNotifications;

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
    @ToString.Include
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @ToString.Include
    private String name; 

    @Column(name = "description", length = 500)
    private String description;
}