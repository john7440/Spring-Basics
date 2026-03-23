package fr.fms.entities;


import javax.persistence.*;

import java.io.Serializable;

@Entity
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String description;
    private double price;

    @ManyToOne
    private Category category;

    public Article() {
    }

    public Article(String brand, String description, double price) {
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

    public Article(String brand, String description, double price, Category category) {
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Article(Long id,String brand, String description, double price) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

    //----------------getters and setters------------------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return (brand + " " + description + " " + price + " " + category.toString());
    }
}
