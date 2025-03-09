package com.cudev.appdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "oder")
public class Oder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ODER")
    private Long idOrder;

    @Column(name = "ID_CUS")
    private Long idCustomer;

    @Column(name = "ID_REPAIRMAM")
    private Long idRepair;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NAME_CUS")
    private String nameCustomer;

    @Column(name = "NUMBER_PHONE_CUS")
    private String numberPhoneCustomer;

    @Column(name = "DESCRIBE")
    private String describe;

    @Column(name = "IMG")
    private String img;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_REPAIR", nullable = false)
    private Repairman repairman;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(Long idRepair) {
        this.idRepair = idRepair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNumberPhoneCustomer() {
        return numberPhoneCustomer;
    }

    public void setNumberPhoneCustomer(String numberPhoneCustomer) {
        this.numberPhoneCustomer = numberPhoneCustomer;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Repairman getRepairman() {
        return repairman;
    }

    public void setRepairman(Repairman repairman) {
        this.repairman = repairman;
    }
}
