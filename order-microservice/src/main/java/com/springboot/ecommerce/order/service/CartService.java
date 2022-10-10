package com.springboot.ecommerce.order.service;

import java.util.List;
import com.springboot.ecommerce.order.model.Cart;

public interface CartService {

	public List<Cart> viewCart(String userId);

	public Cart addCart(Cart cart);

	public Cart addQuantity(String id);

	public Cart minusQuantity(String id) throws Exception;

	public void deleteCart(String prodId);

	public void deleteAllCart(String userId);

}
