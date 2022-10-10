package com.springboot.ecommerce.order.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "User is required")
	private String userId;
	
	private String price;
	
	@CreationTimestamp
	private Date date;
	
	@NotNull(message = "Address is required")
	private String address;
	
	@NotNull(message = "Email is required")
	@Email(message = "Valid Email is required")
	private String email;
	
	@NotNull(message = "Phone is required")
	@Pattern(regexp="(^$|[0-9]{10})", message = "Valid Phone is required")
	private String phone;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetail;

	public Order() { }
	
	public Order(Long id, @NotNull(message = "User is required") String userId, String price, Date date,
			@NotNull(message = "Address is required") String address,
			@NotNull(message = "Email is required") @Email(message = "Valid Email is required") String email,
			@NotNull(message = "Phone is required") @Pattern(regexp = "(^$|[0-9]{10})", message = "Valid Phone is required") String phone,
			List<OrderDetail> orderDetail) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.date = date;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.orderDetail = orderDetail;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", price=" + price + ", date=" + date + ", address=" + address
				+ ", email=" + email + ", phone=" + phone + ", orderDetail=" + orderDetail + "]";
	}

}
