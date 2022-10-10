package com.springboot.ecommerce.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.products.model.Product;
import com.springboot.ecommerce.products.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
//	@Query("select p from Product p fetch join p.subCategory sc where sc.")
//	@Query(value = "SELECT p.id, p.name, p.price, '' as image, p.status, sc.name as subCatName FROM product p JOIN subcategory sc on p.sub_cat_id = sc.id",
//			countQuery = "select count(*) from product",
//			nativeQuery = true)
	
	@Query(value = "SELECT new com.springboot.ecommerce.products.model.Product(pe.id, pe.subCategory.name, pe.name, pe.image, pe.price, pe.status) FROM ProductEntity pe")
	public Page<Product> findAllProductBy(Pageable pageable);

	@Query(value = "SELECT new com.springboot.ecommerce.products.model.Product(pe.id, pe.subCategory.name, pe.name, pe.image, pe.price, pe.status) FROM ProductEntity pe where pe.id = :id")
	public Product findOneById(@Param("id") Long id);
	
	@Query(value = "SELECT new com.springboot.ecommerce.products.model.Product(pe.id, pe.subCategory.name, pe.name, pe.image, pe.price, pe.status) FROM ProductEntity pe where pe.name like %:name%")
	public Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

	@Query(value = "SELECT new com.springboot.ecommerce.products.model.Product(pe.id, pe.subCategory.name, pe.name, pe.image, pe.price, pe.status) FROM ProductEntity pe where pe.subCategory.name like %:subCatName%")
	public Page<Product> findBySubCatNameContaining(@Param("subCatName") String subCatName, Pageable pageable);
	
	@Query(value = "SELECT new com.springboot.ecommerce.products.model.Product(pe.id, pe.subCategory.name, pe.name, pe.image, pe.price, pe.status) FROM ProductEntity pe where pe.price like %:price%")
	public Page<Product> findByPriceContaining(@Param("price") String price, Pageable pageable);
} 
