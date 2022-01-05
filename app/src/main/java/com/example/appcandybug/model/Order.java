package com.example.appcandybug.model;

import androidx.annotation.Nullable;

import java.util.Date;

public class Order {
    private int idAccount;
    private Date dateCreate;
    private String status;
    private String address;
    private Date deliveryDate;
    private int SDT;

    public Order() {
    }

    public Order(int idAccount, Date dateCreate, String status, String address, Date deliveryDate, int SDT) {
        this.idAccount = idAccount;
        this.dateCreate = dateCreate;
        this.status = status;
        this.address = address;
        this.deliveryDate = deliveryDate;
        this.SDT = SDT;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }
}
