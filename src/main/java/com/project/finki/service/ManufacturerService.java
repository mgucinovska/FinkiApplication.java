package com.project.finki.service;

import com.project.finki.model.Manufacturer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Manufacturer findById(Long id) throws RuntimeException;

    Manufacturer addManufacturer(String name, MultipartFile image) throws IOException;

    String deleteManufacturer(Long id) throws RuntimeException;
}
