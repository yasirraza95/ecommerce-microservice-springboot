package com.springboot.ecommerce.order.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
	
	public Order findOneById(Long id);
	
	public List<Order> findByUserId(String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Order o SET o.price = :price WHERE o.id = :id")
	public void updateOrderPrice(@Param("price") String price, @Param("id") Long id);

}
