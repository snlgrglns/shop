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
import com.example.shop.model.ProductColor;
import com.example.shop.repository.ProductColorRepository;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/shop/api/")
public class ProductcolorController {
	@Autowired
	ProductColorRepository productColorRepository;
	
	@DeleteMapping("/color/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteColor(@PathVariable Long id){
		ProductColor productColor = productColorRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Color does not exist for id: " + id));				
		productColorRepository.delete(productColor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/color")
	public ResponseEntity<String> createColor(@RequestBody ProductColor productColor){
//		ObjectMapper objectMapper = new ObjectMapper();
		try {
			productColorRepository.save(productColor);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Saved!", HttpStatus.OK);
			
	}

}
