package com.project.finki.service.impl;

import com.project.finki.model.Manufacturer;
import com.project.finki.repository.ManufacturerRepository;
import com.project.finki.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) throws RuntimeException {
        return manufacturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manufacturer with id: "+ id +" not found"));
    }

    @Override
    public Manufacturer addManufacturer(String name, MultipartFile image) throws IOException {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            manufacturer.setImage(base64Image);
        }
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public String deleteManufacturer(Long id) throws RuntimeException {
        Manufacturer manufacturer = this.findById(id);
        manufacturerRepository.delete(manufacturer);
        return "The manufacturer is deleted";
    }
}
