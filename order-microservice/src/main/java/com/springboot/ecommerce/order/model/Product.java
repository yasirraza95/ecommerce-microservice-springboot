package com.springboot.ecommerce.order.model;

import java.util.List;

public class Product {
	
	private String status;
	private String message;
	private Data data;
	
	public Product() { }
	
	public Product(String status, String message, Data data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Product [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
}
