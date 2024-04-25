package com.javaadvanced.ArtemVinnikLab.controllers;

import com.javaadvanced.ArtemVinnikLab.entities.Product;
import com.javaadvanced.ArtemVinnikLab.repositories.IProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductRepository pRepository;

    @GetMapping(path = "/", produces = "application/json")
    @ResponseBody
    public Iterable<Product> getProducts() {
        return pRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<String> createProductsList(@RequestBody String url) {

        return ResponseEntity.ok("List successfully added");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {

        if (pRepository.existsById(productId)) {
            pRepository.deleteById(productId);
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}