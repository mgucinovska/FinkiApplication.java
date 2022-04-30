package com.project.finki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    @Lob
    private String image;

    @Column(name = "year")
    private String year;

    @Column(name = "color")
    private String color;

    @Column(name = "number_of_copies")
    private Integer numberOfCopies;

    @ManyToOne
    private Manufacturer manufacturer;


    public Car() {
    }

    public Car(Long id, String name, String description, Integer price, String image, String year, String color, Integer numberOfCopies, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.year = year;
        this.color = color;
        this.numberOfCopies = numberOfCopies;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Integer numbersOfCopies) {
        this.numberOfCopies = numbersOfCopies;
    }

}
