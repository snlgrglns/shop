package com.example.shop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	private static String imageDirectoryPath;
	
	public ProductController() throws IOException{
		this.imageDirectoryPath = new ClassPathResource("static/product_images/").getFile().getAbsolutePath();
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping(value="/products", produces= {MediaType.IMAGE_JPEG_VALUE, "application/json"})
	public ResponseEntity<String> createProduct(@RequestParam("productImageFile") MultipartFile productImage, @RequestParam("productDetail") String productDetail){
		//	public ResponseEntity<String> createProduct(@RequestParam("productDetail") Product productDetail){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Product product = objectMapper.readValue(productDetail, Product.class);	
			System.out.println(productDetail);
			
			String imageName = productImage.getOriginalFilename();
			String img_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product_images/").path(imageName).toUriString();
			System.out.println(product);
			Set<ProductImage> productImageObj = new HashSet<ProductImage>();
			productImageObj.add(new ProductImage(img_url));
			product.setProductImage(productImageObj);
			Path fileNamePath=Paths.get(imageDirectoryPath, imageName);
			Files.write(fileNamePath, productImage.getBytes());
//			System.out.println(productImageObj.getImagePath());
//			productImageRepository.save(productImageObj);
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
