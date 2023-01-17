package com.example.shop.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

@Entity
@Transactional
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="status")
	private int status;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName="id")
    private Set<ProductColor> productColor;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName="id")
    private Set<ProductImage> productImage;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName="id")
    private Set<ProductPrice> productPrice;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName="id")
    private Set<ProductSize> productSize;
	
	public Product() {
		
	}
	
	public Product(String name, String description, int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Set<ProductColor> getProductColor() {
		return productColor;
	}

	public void setProductColor(Set<ProductColor> productColor) {
		this.productColor = productColor;
	}

	public Set<ProductImage> getProductImage() {
		return productImage;
	}

	public void setProductImage(Set<ProductImage> productImage) {
		this.productImage = productImage;
	}

	public Set<ProductPrice> getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Set<ProductPrice> productPrice) {
		this.productPrice = productPrice;
	}

	public Set<ProductSize> getProductSize() {
		return productSize;
	}

	public void setProductSize(Set<ProductSize> productSize) {
		this.productSize = productSize;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
