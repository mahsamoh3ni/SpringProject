package com.example.springmvc.springmvc.repository;

import com.example.springmvc.springmvc.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional <ProductEntity> findByName(String name);
}
