package com.springboot.ecommerce.order.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.order.model.Cart;
import com.springboot.ecommerce.order.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>  {

	public Wishlist findOneByUserIdAndProdId(String userId, String prodId);
	
	public List<Wishlist> findByUserId(String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM Wishlist w WHERE w.userId = :id")
	public void deleteAllByUserId(@Param("id") String id);
}
