package com.project.finki.service;

import com.project.finki.dto.CardDetail;
import com.project.finki.dto.ChargeRequest;
import com.project.finki.model.OrderCar;
import com.project.finki.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart findByUsername(String username);

    ShoppingCart createShoppingCart(String username);

    OrderCar addCarToShoppingCart(Long carId, Integer quantity, String username);

    String removeCarFromShoppingCart(Long rowId);

    String buyAllCars(String username, ChargeRequest chargeRequest, CardDetail cardDetail);
}
