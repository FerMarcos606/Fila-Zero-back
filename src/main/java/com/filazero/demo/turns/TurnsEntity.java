package com.filazero.demo.turns;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turns")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TurnsEntity {

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
