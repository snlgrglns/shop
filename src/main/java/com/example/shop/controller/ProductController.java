package com.example.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.model.Product;
import com.example.shop.model.ProductColor;
import com.example.shop.model.ProductDetail;
import com.example.shop.model.ProductImage;
import com.example.shop.model.ProductPrice;
import com.example.shop.model.ProductSize;
import com.example.shop.repository.ProductColorRepository;
import com.example.shop.repository.ProductImageRepository;
import com.example.shop.repository.ProductPriceRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ProductSizeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/shop/api/")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductPriceRepository productPriceRepository;
	
	@Autowired
	private ProductSizeRepository productSizeRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product){
//		ObjectMapper objectMapper = new ObjectMapper();
		try {
			productRepository.save(product);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Saved!", HttpStatus.OK);
			
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable long id){
		Optional<Product> pr = productRepository.findById(id);
		return pr;
		
	}	
}
