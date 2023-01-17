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
@Table(name="product_color")
public class ProductColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne //mappedBy="product", 
//	@JoinColumn(name = "product_id", nullable=false)
//    private Product product;
	
	@Column(name="product_id")
	private int productId;
	
	public int getProductId() {
		return productId;
	}

	public void setProduct_id(int productId) {
		this.productId = productId;
	}

	@Column(name="color_name")
	private String colorName;
	
	public ProductColor() {
		
	}
	
	

	public ProductColor(Product product, String colorName) {
		super();
//		this.product = product;
		this.colorName = colorName;
	}

	public int getId() {
		return id;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	
}
