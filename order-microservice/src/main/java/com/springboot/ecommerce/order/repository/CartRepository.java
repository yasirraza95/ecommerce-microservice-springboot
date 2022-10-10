package com.springboot.ecommerce.order.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.order.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//	@Query(value = "SELECT c.quantity FROM cart c WHERE id = :id")
//	public Cart findQuantityById(Long id);
	
	public Cart findOneById(Long id);
	
	public Cart findOneByUserIdAndProdId(String userId, String prodId);
	
	public List<Cart> findByUserId(String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Cart c SET c.quantity = :quantity WHERE c.id = :id")
	public void updateQuantityById(@Param("quantity") String quantity, @Param("id") Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM Cart c WHERE c.userId = :id")
	public void deleteAllByUserId(@Param("id") String id);
	
//	@Query(value = "UPDATE cart c SET c.quantity = ?1 WHERE c.id = ?2")
//	public Cart changeQuantityById(String quantity, String id);
}
