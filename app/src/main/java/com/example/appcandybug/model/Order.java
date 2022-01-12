package com.example.appcandybug.model;

import androidx.annotation.Nullable;

import java.util.Date;

public class Order {
    private int Id;
    private int IdAcc;
    private Date DateCreate;
    private String Status;
    private String Address;
    private Date DeliveryDate;
    private int SDT;

    public Order() {
    }

    public Order(int IdAcc, Date DateCreate, String Status, String Address, Date DeliveryDate, int SDT) {
        this.IdAcc = IdAcc;
        this.DateCreate = DateCreate;
        this.Status = Status;
        this.Address = Address;
        this.DeliveryDate = DeliveryDate;
        this.SDT = SDT;
    }

    public Order(int id, int idAcc, Date dateCreate, String status, String address, Date deliveryDate, int SDT) {
        Id = id;
        IdAcc = idAcc;
        DateCreate = dateCreate;
        Status = status;
        Address = address;
        DeliveryDate = deliveryDate;
        this.SDT = SDT;
    }

    public Order(int idAcc, String status, String address, int SDT) {
        IdAcc = idAcc;
        Status = status;
        Address = address;
        this.SDT = SDT;
    }

    public int getIdAcc() {
        return IdAcc;
    }

    public void setIdAcc(int idAcc) {
        IdAcc = idAcc;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }
}
