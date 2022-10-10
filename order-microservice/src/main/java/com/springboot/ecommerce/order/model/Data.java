package com.springboot.ecommerce.order.model;

public class Data {

	private Long id;
	private String name;
	private String image;
	private String price;
	private String status;
	
	public Data() { }
	
	public Data(Long id, String name, String image, String price, String status ) {
		super();
		this.id = id;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", status=" + status
				+ "]";
	}
		
}
