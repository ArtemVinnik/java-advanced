package com.javaadvanced.ArtemVinnikLab.repositories;

import com.javaadvanced.ArtemVinnikLab.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
}
