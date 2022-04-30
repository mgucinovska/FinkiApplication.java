package com.project.finki.controller;

import com.project.finki.model.Manufacturer;
import com.project.finki.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Manufacturer>> findAll() {
        return ResponseEntity.ok(manufacturerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id) throws RuntimeException {
        return ResponseEntity.ok(manufacturerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Manufacturer> addManufacturer(@RequestParam(name = "name") String name, @RequestParam(name = "image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(manufacturerService.addManufacturer(name, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManufacturer(@PathVariable Long id) throws RuntimeException {
        return ResponseEntity.ok(manufacturerService.deleteManufacturer(id));
    }
}

