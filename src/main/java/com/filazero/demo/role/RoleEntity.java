package com.filazero.demo.role;

import com.filazero.demo.customer.CustomerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany; // ¡Importante!
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role; 

    @Column(nullable = false, unique = true, length = 50)
    private String name; 

   
    // RELAtion MANY-TO-MANY INVERTED ---    
   
    // @ManyToMany(mappedBy = "roles")
    // @Builder.Default /**
    //  *
    //  */
    // private Set<CustomerEntity> customers = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<CustomerEntity> customer = new HashSet<>();

}
