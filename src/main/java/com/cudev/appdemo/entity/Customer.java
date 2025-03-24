package com.cudev.appdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "NAME_CUSTOMER")
    private String nameCustomer;

    @Column(name = "NUMBER_PHONE",unique = true)
    private String numberPhone;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "ID_USER")
    private User user;

//    @OneToMany(mappedBy = "customer")
//    private List<Oder> orders;

}
