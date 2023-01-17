package com.example.shop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.ClassPathResource;

import com.example.shop.model.ProductImage;
import com.example.shop.repository.ProductImageRepository;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/shop/api/")
public class ProductimageController {
	@Autowired
	ProductImageRepository productImageRepository;
	
//	private static String imageDirectoryPath = System.getProperty("user.dir")+"/src/main/resources/static/product_images/";
	private static String imageDirectoryPath;
	
	public ProductimageController() throws IOException{
		this.imageDirectoryPath = new ClassPathResource("static/product_images/").getFile().getAbsolutePath();
	}
	
	@PostMapping(value="/image", produces= {MediaType.IMAGE_JPEG_VALUE, "application/json"})
	public ResponseEntity<String> saveImage(@RequestParam("productImageFile") MultipartFile productImage, @RequestParam("product_id") int product_id){
//		ObjectMapper objectMapper = new ObjectMapper();
//		makeDirectoryIfNotExist(imageDirectoryPath);
//		File directory = new File(this.imageDirectoryPath);
//		if(!directory.exists()) {
//			directory.mkdir();
//		}
		try {
//			String filePath = imageDirectoryPath+productImage.getOriginalFilename();
//			ProductImage fileData = productImageRepository.save(ProductImage.builder()).name
			String imageName = productImage.getOriginalFilename();
			String img_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product_images/").path(imageName).toUriString();
			System.out.println(img_url);
			ProductImage productImageObj = new ProductImage(product_id, img_url);
			Path fileNamePath=Paths.get(imageDirectoryPath, imageName);
			Files.write(fileNamePath, productImage.getBytes());
			System.out.println(productImageObj.getImagePath());
			productImageRepository.save(productImageObj);
			return new ResponseEntity<>("Saved", HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.toString(), HttpStatus.OK);
		}			
	}
	
//	@GetMapping(value = "/image/",produces = MediaType.IMAGE_PNG_VALUE)
//	public @ResponseBody byte[] returnImage() throws IOException {
////		System.out.println(product_id);
////		List<ProductImage> productImages = productImageRepository.findAllById(product_id);
//		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product_images/").path("Screenshot.png").toUriString();
//		InputStream in = getClass()
//	      .getResourceAsStream(url);
//	    return IOUtils.toByteArray(in);
//	    
//	    
//	}
	
	@GetMapping(value = "/image/")
	public ResponseEntity<String> getImage() throws IOException {
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product_images/").path("Screenshot.png").toUriString();
	    return ResponseEntity
                .ok(url);
	}
}
