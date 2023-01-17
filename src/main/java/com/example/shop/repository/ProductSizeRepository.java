package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {

}
