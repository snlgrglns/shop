package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{

}
