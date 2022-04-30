package com.project.finki.service.impl;

import com.project.finki.dto.CardDetail;
import com.project.finki.dto.ChargeRequest;
import com.project.finki.dto.exception.ShoppingCartException;
import com.project.finki.dto.exception.TransactionFailedException;
import com.project.finki.enumerations.CartStatus;
import com.project.finki.model.*;
import com.project.finki.repository.OrderCarRepository;
import com.project.finki.repository.ShoppingCartRepository;
import com.project.finki.repository.UserRepository;
import com.project.finki.service.CarService;
import com.project.finki.service.ManufacturerService;
import com.project.finki.service.PaymentService;
import com.project.finki.service.ShoppingCartService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderCarRepository orderCarRepository;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public ShoppingCart findByUsername(String username) {
        return shoppingCartRepository.findByUserUsername(username);
    }

    @Override
    public ShoppingCart createShoppingCart(String username) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Optional<User> user = userRepository.findByUsername(username);
        shoppingCart.setStatus(CartStatus.CREATED);
        shoppingCart.setUser(user.get());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public OrderCar addCarToShoppingCart(Long carId, Integer quantity, String username) {
        Car car = carService.findById(carId);

        if (car.getNumberOfCopies() < quantity) {
            return null;
        }

        ShoppingCart shoppingCart = this.findByUsername(username);

        for (OrderCar item: shoppingCart.getOrderCars()) {
            if (item.getCarId() == carId) {
                throw  new ShoppingCartException("Car is already in shopping cart");
            }
        }

        Manufacturer manufacturer = manufacturerService.findById(car.getManufacturer().getId());
        OrderCar orderCar = new OrderCar();

        orderCar.setCarId(car.getId());
        orderCar.setCarName(car.getName());
        orderCar.setCarPrice(car.getPrice());
        orderCar.setQuantity(quantity);
        orderCar.setManufacturerName(manufacturer.getName());
        orderCar.setShoppingCart(shoppingCart);

        return orderCarRepository.save(orderCar);
    }

    @Override
    public String removeCarFromShoppingCart(Long rowId) {
        orderCarRepository.deleteById(rowId);
        return "Car remove from Shopping Cart";
    }

    @Override
    @Transactional
    public String buyAllCars(String username, ChargeRequest chargeRequest, CardDetail cardDetail) {
        ShoppingCart shoppingCart = this.findByUsername(username);

        chargeRequest.setAmount(chargeRequest.getAmount() * 100);

        Map<String, Object> card = new HashMap<>();
        card.put("number", cardDetail.getCardNumber());
        card.put("exp_month", cardDetail.getExpMonth());
        card.put("exp_year", cardDetail.getExpYear());
        card.put("cvc", cardDetail.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token = null;

        try {
            token = Token.create(params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        Charge charge = null;

        chargeRequest.setStripeToken(token.getId());

        try {
            charge = this.paymentService.pay(chargeRequest);
        } catch (CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e) {
            throw new TransactionFailedException(username, e.getMessage());
        }


        for (OrderCar item: shoppingCart.getOrderCars()) {
            Car car = carService.findById(item.getCarId());
            if (car.getNumberOfCopies() < item.getQuantity()) {
                return null;
            }
            car.setNumberOfCopies(car.getNumberOfCopies() - item.getQuantity());
            carService.updateQuantity(car.getId(), car.getNumberOfCopies());
        }

        return "Successfully purchased";
    }
}
