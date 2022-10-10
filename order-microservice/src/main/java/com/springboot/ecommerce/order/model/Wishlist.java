package com.springboot.ecommerce.order.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "User is required")
	private String userId;
	
	@NotNull(message = "Product is required")
	private String prodId;
	
	@Transient
	private String prodName;
	
	@Transient
	private String prodImage;
	
	@Transient
	private String prodPrice;

	@CreationTimestamp
	private Date date;

	public Wishlist() {}
	
	public Wishlist(Long id, @NotNull(message = "User is required") String userId,
			@NotNull(message = "Product is required") String prodId, Date date) {
		super();
		this.id = id;
		this.userId = userId;
		this.prodId = prodId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", userId=" + userId + ", prodId=" + prodId + ", prodName=" + prodName
				+ ", prodImage=" + prodImage + ", prodPrice=" + prodPrice + ", date=" + date + "]";
	}

}
