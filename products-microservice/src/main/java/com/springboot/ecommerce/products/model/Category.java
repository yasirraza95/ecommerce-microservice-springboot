package com.springboot.ecommerce.products.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.springboot.ecommerce.products.enums.Status;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Name is required")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private Status status = Status.ACTIVE;
	
	@OneToMany(mappedBy="category")
	List<Subcategory> subCategory;

	public Category() {}

	public Category(Long id, @NotNull(message = "Name is required") String name,
			@NotNull(message = "Status is required") Status status) {
		super();
		this.id = id;
		this.name = name;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
