package com.example.appcandybug.model;

public class OrderInfo {
    private int IdOrder;
    private int IdProduct;
    private int Quantity;
    private double Total;
    private String Image;

    public OrderInfo() {
    }

    public OrderInfo(int idOrder, int idProduct, int quantity, double total) {
        IdOrder = idOrder;
        IdProduct = idProduct;
        Quantity = quantity;
        Total = total;
    }

    public OrderInfo(int quantity, double total, String image) {
        Quantity = quantity;
        Total = total;
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int idOrder) {
        IdOrder = idOrder;
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
