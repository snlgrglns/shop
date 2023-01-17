package com.example.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.ProductSize;
import com.example.shop.repository.ProductSizeRepository;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/shop/api/")
public class ProductsizeController {
	
	@Autowired
	ProductSizeRepository productSizeRepository;

	@DeleteMapping("/size/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteColor(@PathVariable Long id){
		ProductSize productSize = productSizeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Size does not exist for id: " + id));				
		productSizeRepository.delete(productSize);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/size")
	public ResponseEntity<String> createSize(@RequestBody ProductSize productSize){
//		ObjectMapper objectMapper = new ObjectMapper();
		try {
			productSizeRepository.save(productSize);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Saved!", HttpStatus.OK);
			
	}
}
