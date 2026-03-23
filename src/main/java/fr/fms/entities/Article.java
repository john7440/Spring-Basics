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
    }

    public Article(Long id,String brand, String description, double price) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

}
