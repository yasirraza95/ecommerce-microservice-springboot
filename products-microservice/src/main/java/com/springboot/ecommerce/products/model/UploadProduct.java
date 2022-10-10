package com.springboot.ecommerce.products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.ecommerce.products.enums.Status;

public class UploadProduct {
	
	private Long id;
	@JsonIgnore
	private String subCatId;
	private String subCatName;
	private String name;
	private String image;
	private String price;
	private Status status;
	
	public UploadProduct() {}
	
	public UploadProduct(Long id, String subCatId, String subCatName, String name, String image, String price, Status status) {
		this();
		this.id = id;
		this.subCatId = subCatId;
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

	public String getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
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
		return "UploadProduct [id=" + id + ", subCatId=" + subCatId + ", subCatName=" + subCatName + ", name=" + name
				+ ", image=" + image + ", price=" + price + ", status=" + status + "]";
	}

}
