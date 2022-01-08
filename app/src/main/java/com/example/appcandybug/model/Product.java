package com.example.appcandybug.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Locale;

public class Product implements Serializable {
    private int Id;
    private String Name;
    private String Category;
    private Double Price;
    private int Quantity;
    private int Discount;
    private String Image;



    private String Description;

    public Product() {
    }

    public Product(int id, String name, String category, Double price, int quantity, int discount, String image ,String description) {
        Id = id;
        Name = name;
        Category = category;
        Price = price;
        Quantity = quantity;
        Discount = discount;
        Image = image;
        Description = description;
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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
