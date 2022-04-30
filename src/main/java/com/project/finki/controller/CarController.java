package com.project.finki.controller;

import com.project.finki.model.Car;
import com.project.finki.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) throws RuntimeException {
        return  carService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "price") Integer price,
                                      @RequestParam(name = "year") String year,
                                      @RequestParam(name = "color") String color,
                                      @RequestParam(name = "numberOfCopies") Integer numberOfCopies,
                                      @RequestParam(name = "manufacturerId") Long manufacturerId,
                                      @RequestParam(name = "image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(carService.addCar(name, description, price, year, color, numberOfCopies, manufacturerId, image));
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "price") Integer price,
                         @RequestParam(name = "year") String year,
                         @RequestParam(name = "color") String color,
                         @RequestParam(name = "numberOfCopies") Integer numberOfCopies,
                         @RequestParam(name = "manufacturerId") Long manufacturerId,
                         @RequestParam(name = "image", required = false) MultipartFile image) throws RuntimeException, IOException {
        return carService.updateCar(id, name, description, price, year, color, numberOfCopies, manufacturerId, image);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id)  throws RuntimeException {
        return carService.deleteCar(id);
    }

    @GetMapping("/manufacturer/{id}")
    public List<Car> findCarByManufacturerId(@PathVariable Long id){
        return carService.findCarByManufacturerId(id);
    }
}
