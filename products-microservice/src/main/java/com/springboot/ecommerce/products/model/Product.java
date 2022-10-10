package com.springboot.ecommerce.products.model;

import com.springboot.ecommerce.products.enums.Status;

public class Product {
	
	private Long id;
	private String subCatName;
	private String name;
	private String image;
	private String price;
	private Status status;
	
	public Product() {}
	
	public Product(Long id, String subCatName, String name, String image, String price, Status status) {
		this();
		this.id = id;
		this.subCatName = subCatName;
		this.name = name;
		this.image = image;
		this.price = price;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
		
	@Override
	public String toString() {
		return "Product [id=" + id + ", subCatName=" + subCatName + ", name=" + name + ", image=" + image + ", price="
				+ price + ", status=" + status + "]";
	}

}
