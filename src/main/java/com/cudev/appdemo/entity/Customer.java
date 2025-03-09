package com.cudev.appdemo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "NAME_CUSTOMER")
    private String nameCustomer;

    @Column(name = "NUMBER_PHONE")
    private String numberPhone;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "ID_USER")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Oder> orders;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Oder> getOrders() {
        return orders;
    }

    public void setOrders(List<Oder> orders) {
        this.orders = orders;
    }
}
