package com.filazero.demo.customer;

import java.util.HashSet;
import java.util.Set;

import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.turns.TurnsEntity;
import com.filazero.demo.delivery.DeliveryEntity;
import com.filazero.demo.nofications.NotificationsEntity;
import com.filazero.demo.profile.ProfileEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerEntity {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password; 

    // --- RELATIONS ---

    // ProfileEntity FK.
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProfileEntity profile;

    // intermediate table
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "customer_roles",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    
    // @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<DeliveryEntity> deliveries = new HashSet<>();
   
    // @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<NotificationsEntity> notifications = new HashSet<>();

    // // 5. One-to-Many: Turnos (El campo debe llamarse 'customer' en TurnsEntity)
    // @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<TurnsEntity> turns = new HashSet<>(); 
}


