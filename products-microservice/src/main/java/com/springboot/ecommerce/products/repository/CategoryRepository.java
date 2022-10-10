package com.springboot.ecommerce.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.products.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Category findOneById(Long id);
}
