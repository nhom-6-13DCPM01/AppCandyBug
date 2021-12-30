package com.example.appcandybug.model;

import java.text.DecimalFormat;
import java.util.Locale;

public class Product {
    private int Id;
    private String Name;
    private String Category;
    private DecimalFormat Price;
    private int Quantity;
    private int Discount;
    private String Image;

    public Product() {
    }

    public Product(int id, String name, String category, DecimalFormat price, int quantity, int discount, String image) {
        Id = id;
        Name = name;
        Category = category;
        Price = price;
        Quantity = quantity;
        Discount = discount;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public DecimalFormat getPrice() {
        return Price;
    }

    public void setPrice(DecimalFormat price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
