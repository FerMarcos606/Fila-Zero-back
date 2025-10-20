package com.filazero.demo.queue;


import com.filazero.demo.turns.TurnsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "queue")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer position;

    @OneToOne
    @JoinColumn(name = "turn_id", nullable = false)
    private TurnsEntity turn;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = true)
    private Integer priority;

    // Opcional: turns urgents
    @Column(nullable = true)
    private String status;
}
