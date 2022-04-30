package com.project.finki.service.impl;

import com.project.finki.model.Car;
import com.project.finki.model.Manufacturer;
import com.project.finki.repository.CarRepository;
import com.project.finki.service.CarService;
import com.project.finki.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) throws RuntimeException {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car with id: "+ id +" not found"));
    }

    @Override
    public Car addCar(String name, String description, Integer price, String year, String color, Integer numberOfCopies, Long manufacturerId, MultipartFile image) throws IOException {
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        Car car = new Car();

        car.setManufacturer(manufacturer);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            car.setImage(base64Image);
        }

        car.setName(name);
        car.setColor(color);
        car.setDescription(description);
        car.setNumberOfCopies(numberOfCopies);
        car.setPrice(price);
        car.setYear(year);

        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, String name, String description, Integer price, String year, String color, Integer numberOfCopies, Long manufacturerId, MultipartFile image) throws RuntimeException, IOException {
        Car response = this.findById(id);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            response.setImage(base64Image);
        }

        response.setName(name);
        response.setDescription(description);
        response.setPrice(price);
        response.setYear(year);
        response.setNumberOfCopies(numberOfCopies);
        response.setColor(color);

        return carRepository.save(response);
    }

    @Override
    public String deleteCar(Long id) throws RuntimeException {
        Car response = this.findById(id);
        carRepository.delete(response);

        return "The car is deleted";
    }

    @Override
    public Car updateQuantity(Long carId, Integer numberOfCopies) {
        Car response = this.findById(carId);
        response.setNumberOfCopies(numberOfCopies);

        return carRepository.save(response);
    }

    @Override
    public List<Car> findCarByManufacturerId(Long id) {
        return carRepository.findByManufacturerId(id);
    }
}
