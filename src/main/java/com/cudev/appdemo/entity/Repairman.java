package com.cudev.appdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "repairman")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repairman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REPAIR")
    private Long idRepairman;

    @Column(name = "NAME_REPAIR")
    private String nameRepairman;

    @Column(name = "NUMBER_PHONE")
    private String numberPhone;


    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATUS")
    private int status;

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    public User user;

}
