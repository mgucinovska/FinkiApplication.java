package com.project.finki.controller;

import com.project.finki.dto.CardDetail;
import com.project.finki.dto.ChargeRequest;
import com.project.finki.model.OrderCar;
import com.project.finki.model.ShoppingCart;
import com.project.finki.service.ShoppingCartService;
import com.project.finki.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/findByUsername")
    public ShoppingCart findByUsername(@RequestHeader (name="Authorization") String jwtToken) {
        String token = jwtToken.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        return shoppingCartService.findByUsername(username);
    }

    @PostMapping
    public ShoppingCart createShoppingCart(@RequestHeader (name="Authorization") String jwtToken) {
        String token = jwtToken.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        return shoppingCartService.createShoppingCart(username);
    }

    @PutMapping("/addCar/{carId}")
    public OrderCar addCarToShoppingCart(@PathVariable Long carId, @RequestParam("quantity") Integer quantity, @RequestHeader (name="Authorization") String jwtToken) {
        String token = jwtToken.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        OrderCar response = shoppingCartService.addCarToShoppingCart(carId, quantity, username);

        if (response == null) {
            return null;
        } else {
            return response;
        }
    }

    @DeleteMapping("/{rowId}")
    public String removeCarFromShoppingCart(@PathVariable Long rowId) {
        return shoppingCartService.removeCarFromShoppingCart(rowId);
    }

    @PutMapping("/buyCars")
    public String buyAllCars(@RequestHeader (name="Authorization") String jwtToken, ChargeRequest chargeRequest, CardDetail cardDetail) {
        String token = jwtToken.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        String response = shoppingCartService.buyAllCars(username, chargeRequest, cardDetail);

        if (response == null) {
            return null;
        } else {
            return response;
        }
    }
}
