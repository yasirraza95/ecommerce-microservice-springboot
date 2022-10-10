package com.springboot.ecommerce.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Order Id is required")
	private String orderId;
	
	@NotNull(message = "Product is required")
	private String prodId;
	
	@Transient
	private String prodName;

	@Transient
	private String prodImage;

	@Transient
	private String prodPrice;

	@NotNull(message = "Quantity is required")
	private String quantity;
	
	@NotNull(message = "Price is required")
	private String price;
	
	@NotNull(message = "Total Price is required")
	private String totalPrice;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="orderId", insertable=false, updatable=false)
	private Order order;

	public OrderDetail() { }
	
	public OrderDetail(Long id, @NotNull(message = "Order Id is required") String orderId,
			@NotNull(message = "Product is required") String prodId,
			@NotNull(message = "Quantity is required") String quantity,
			@NotNull(message = "Price is required") String price,
			@NotNull(message = "Total Price is required") String totalPrice, Order order) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.prodId = prodId;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", prodId=" + prodId + ", prodName=" + prodName
				+ ", prodImage=" + prodImage + ", prodPrice=" + prodPrice + ", quantity=" + quantity + ", price="
				+ price + ", totalPrice=" + totalPrice + ", order=" + order + "]";
	}



}
