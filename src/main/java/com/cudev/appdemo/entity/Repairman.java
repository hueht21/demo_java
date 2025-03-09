package com.cudev.appdemo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "repairman")
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

    @OneToMany(mappedBy = "repairman")
    private List<Oder> listOder;

    public Long getIdRepairman() {
        return idRepairman;
    }

    public void setIdRepairman(Long idRepairman) {
        this.idRepairman = idRepairman;
    }

    public String getNameRepairman() {
        return nameRepairman;
    }

    public void setNameRepairman(String nameRepairman) {
        this.nameRepairman = nameRepairman;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Oder> getListOder() {
        return listOder;
    }

    public void setListOder(List<Oder> listOder) {
        this.listOder = listOder;
    }
}
