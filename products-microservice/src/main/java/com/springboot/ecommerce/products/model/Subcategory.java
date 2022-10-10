package com.springboot.ecommerce.products.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.springboot.ecommerce.products.enums.Status;

@Entity
public class Subcategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Category Id is required")
	private String catId;
	
	@NotNull(message = "Name is required")
	private String name;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private Status status = Status.ACTIVE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="catId", insertable=false, updatable=false)
	private Category category;
	
	@OneToMany(mappedBy="subCategory")
	List<ProductEntity> product;

	public Subcategory() {}

	public Subcategory(Long id, @NotNull(message = "Category Id is required") String catId,
			@NotNull(message = "Name is required") String name,
			@NotNull(message = "Status is required") Status status) {
		super();
		this.id = id;
		this.catId = catId;
		this.name = name;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", catId=" + catId + ", name=" + name + ", status=" + status + "]";
	}
	
}
