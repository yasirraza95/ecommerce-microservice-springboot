package com.springboot.ecommerce.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.ecommerce.products.enums.Status;

@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Sub Category Id is required")
	private String subCatId;

	@Column(unique = true)
	@NotNull(message = "Name is required")
	private String name;
	
	@NotNull(message = "Image is required")
	private String image;
	
	@NotNull(message = "Price is required")
	private String price;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private Status status = Status.ACTIVE;
	
	@ManyToOne
	@JoinColumn(name="subCatId", insertable=false, updatable=false)
	private Subcategory subCategory;
	
	@Transient
	private String subCatName;
	

	public ProductEntity() { }
	
	public ProductEntity(Long id, @NotNull(message = "Sub Category Id is required") String subCatId,
			@NotNull(message = "Name is required") String name, @NotNull(message = "Image is required") String image,
			@NotNull(message = "Price is required") String price) {
		super();
		this.id = id;
		this.subCatId = subCatId;
		this.name = name;
		this.image = image;
		this.price = price;
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

	
	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName() {
		this.subCatName = this.subCategory.getName();
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", subCatId=" + subCatId + ", name=" + name + ", image=" + image + ", price="
				+ price + ", status=" + status + "]";
	}
}
