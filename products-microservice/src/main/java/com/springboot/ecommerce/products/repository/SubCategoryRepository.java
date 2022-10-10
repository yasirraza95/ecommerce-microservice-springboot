package com.springboot.ecommerce.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.products.model.Subcategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory, Long> {

	public Subcategory findOneById(Long id);
}