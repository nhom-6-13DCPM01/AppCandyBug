package com.example.appcandybug.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

public class Mycart {
    private int Id;
    private String DateCreate;
    private String Status;
    private String Address;
    private String DeliveryDate;
    private int SDT;
    private Double tongTien;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
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

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
}
