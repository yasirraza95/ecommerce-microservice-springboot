package com.springboot.ecommerce.order.service;

import java.util.List;
import com.springboot.ecommerce.order.model.Wishlist;

public interface WishlistService {

	public List<Wishlist> viewWishlist(String userId);

	public Wishlist addWishlist(Wishlist wishlist) throws Exception;

	public void deleteWishlist(String id);

	public void deleteAllWishlist(String id);

}