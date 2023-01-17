package com.example.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product_image")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne
//	@JoinColumn(name = "product_id", nullable=false)
//    private Product product;
	
	@Column(name="image_path")
	private String imagePath;
	
	@Column(name="product_id")
	private int productId;
	
	public ProductImage() {
		
	}	
	
	public ProductImage(int productId, String imagePath) {
		super();
		this.imagePath = imagePath;
		this.productId = productId;
	}

	public ProductImage(String imagePath) {
		super();
		this.imagePath = imagePath;
	}


	public int getId() {
		return id;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProduct_id(int productId) {
		this.productId = productId;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
