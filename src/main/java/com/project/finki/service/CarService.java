package com.project.finki.service;

import com.project.finki.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(Long id) throws RuntimeException;

    Car addCar(String name, String description, Integer price, String year, String color, Integer numberOfCopies, Long manufacturerId, MultipartFile image) throws IOException;

    Car updateCar(Long id, String name, String description, Integer price, String year, String color, Integer numberOfCopies, Long manufacturerId, MultipartFile image) throws RuntimeException, IOException;

    String deleteCar(Long id) throws RuntimeException;

    Car updateQuantity(Long carId, Integer numberOfCopies);

    List<Car> findCarByManufacturerId(Long id);
}
