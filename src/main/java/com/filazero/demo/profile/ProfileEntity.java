package com.filazero.demo.profile;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.profile.ProfileEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profile;

    @Column(name = "avatar_image", nullable = true, length = 100000) // bytes
    private String avatar;


    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String firstSurname;

    @Column(nullable = false, length = 150)
    private String secondSurname;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

     @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private CustomerEntity customer;  

}