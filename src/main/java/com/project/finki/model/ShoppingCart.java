package com.project.finki.model;

import com.project.finki.enumerations.CartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CartStatus status = CartStatus.CREATED;

    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "shoppingCart")
    private Set<OrderCar> orderCars = new HashSet<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, CartStatus status, LocalDateTime createDate, Set<OrderCar> orderCars) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.orderCars = orderCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

//    public User getUser() {
//        return user;
//    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderCar> getOrderCars() {
        return orderCars;
    }

    public void setOrderCars(Set<OrderCar> orderCars) {
        this.orderCars = orderCars;
    }
}
