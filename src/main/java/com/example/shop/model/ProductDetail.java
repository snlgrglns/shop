package com.example.shop.model;

import java.util.Set;

public class ProductDetail {
	private Product product;
	private Set<ProductColor> productColor;
    private Set<ProductImage> productImage;
    private Set<ProductPrice> productPrice;
    private Set<ProductSize> productSize;
	
	public ProductDetail() {
		
	}
	
    public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
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
}