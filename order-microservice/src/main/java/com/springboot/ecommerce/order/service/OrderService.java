package com.springboot.ecommerce.order.service;

import java.util.List;

import com.springboot.ecommerce.order.model.Order;
import com.springboot.ecommerce.order.model.Product;

public interface OrderService {

	public List<Order> viewOrderByUserId(String userId);

	public Order placeOrder(Order order) throws Exception;

	public Product getProdDetail(int id);

	public void deleteById(String id);

}
