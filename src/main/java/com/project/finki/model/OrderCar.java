package com.project.finki.model;

import javax.persistence.*;

@Entity
@Table(name = "oreder_cars")
public class OrderCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "car_name")
    private String carName;

    @Column(name = "car_price")
    private Integer carPrice;

    @Column(name = "manufaturer_name")
    private String manufacturerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @Column(name = "order_quantity")
    private Integer quantity;

    public OrderCar() {
    }


    public OrderCar(Long id, Long carId, String carName, Integer carPrice, ShoppingCart shoppingCart, Integer quantity, String manufacturerName) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.carPrice = carPrice;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
        this.manufacturerName = manufacturerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

//    public ShoppingCart getShoppingCart() {
//        return shoppingCart;
//    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Integer carPrice) {
        this.carPrice = carPrice;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
