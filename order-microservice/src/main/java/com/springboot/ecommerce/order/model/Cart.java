package com.springboot.ecommerce.order.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "User is required")
	private String userId;

//	@JsonIgnore
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

	@CreationTimestamp
	private Date date;

	public Cart() { }
	
	public Cart(Long id, @NotNull(message = "User is required") String userId,
			@NotNull(message = "Product is required") String prodId,
			@NotNull(message = "Quantity is required") String quantity, Date date) {
		super();
		this.id = id;
		this.userId = userId;
		this.prodId = prodId;
		this.quantity = quantity;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", prodId=" + prodId + ", prodName=" + prodName
				+ ", prodImage=" + prodImage + ", prodPrice=" + prodPrice + ", quantity=" + quantity + ", date=" + date
				+ "]";
	}

}
