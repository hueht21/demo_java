package com.cudev.appdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "oder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Oder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ODER")
    private Long idOrder;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NAME_CUS")
    private String nameCustomer;

    @Column(name = "NUMBER_PHONE_CUS")
    private String numberPhoneCustomer;

    @Column(name = "DESCRIPTION")
    private String describe;

    @Column(name = "IMG")
    private String img;

    @Column(name = "status_code")
    private int statusCode;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_REPAIR")
    private Repairman repairman;

}
