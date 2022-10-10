package com.springboot.ecommerce.order.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.order.model.Cart;
import com.springboot.ecommerce.order.model.Product;
import com.springboot.ecommerce.order.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService, ProductService {

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductServiceClient productServiceClient;

	@Override
	public List<Cart> viewCart(String userId) {
		List<Cart> data = (List<Cart>) cartRepo.findByUserId(userId);

		if (data.isEmpty()) {
			throw new EntityNotFoundException("No Cart found");
		} else {
			for (Cart result : data) {
				Product product = this.getProdDetail(Integer.parseInt(result.getProdId()));
				String prodName = product.getData().getName();
				String prodImage = product.getData().getImage();
				String price = product.getData().getPrice();

				result.setProdName(prodName);
				result.setProdImage(prodImage);
				result.setProdPrice(price);
			}

			return data;
		}
	}

	@Override
	public Cart addCart(Cart cart) {
		try {
			Cart data = cartRepo.findOneByUserIdAndProdId(cart.getUserId(), cart.getProdId());
			if (data != null) {
				// FIXME
				String oldQuantity = data.getQuantity();
				int newQuantity = Integer.valueOf(oldQuantity);
				newQuantity++;
				cartRepo.updateQuantityById(String.valueOf(newQuantity), data.getId());
				Cart newData = cartRepo.findOneById(data.getId());

				Product product = this.getProdDetail(Integer.parseInt(newData.getProdId()));
				String prodName = product.getData().getName();
				String prodImage = product.getData().getImage();
				String price = product.getData().getPrice();

				newData.setProdName(prodName);
				newData.setProdImage(prodImage);
				newData.setProdPrice(price);

				return newData;

			} else {
				Cart result = cartRepo.save(cart);
				if (result.getId() > 0) {

					Product product = this.getProdDetail(Integer.parseInt(result.getProdId()));
					String prodName = product.getData().getName();
					String prodImage = product.getData().getImage();
					String price = product.getData().getPrice();

					result.setProdName(prodName);
					result.setProdImage(prodImage);
					result.setProdPrice(price);

					return result;
				}

				throw new EntityNotFoundException("Error inserting product information");

			}

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Cart addQuantity(String id) {
		try {
			Cart data = cartRepo.findOneById(Long.parseLong(id));
			if (data != null) {
				String oldQuantity = data.getQuantity();
				int newQuantity = Integer.valueOf(oldQuantity);
				newQuantity++;
				cartRepo.updateQuantityById(String.valueOf(newQuantity), Long.parseLong(id));
				Cart newData = cartRepo.findOneById(Long.parseLong(id));
				return newData;

			} else {
				throw new EntityNotFoundException("No cart data found");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Cart minusQuantity(String id) throws Exception {
		try {
			Cart data = cartRepo.findOneById(Long.parseLong(id));
			if (data != null) {
				String oldQuantity = data.getQuantity();
				int newQuantity = Integer.valueOf(oldQuantity);
				newQuantity--;
				if (newQuantity >= 1) {
					cartRepo.updateQuantityById(String.valueOf(newQuantity), Long.parseLong(id));
					Cart newData = cartRepo.findOneById(Long.parseLong(id));
					return newData;
				} else {
					throw new Exception("Quantity can't be less than 1");
				}

			} else {
				throw new EntityNotFoundException("No cart data found");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteCart(String prodId) {
		try {
			cartRepo.deleteById(Long.parseLong(prodId));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteAllCart(String userId) {
		try {
			List<Cart> data = cartRepo.findByUserId(userId);
			if (data.size() > 0) {
				cartRepo.deleteAllByUserId(userId);
			} else {
				throw new EntityNotFoundException("No Product found");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Product getProdDetail(int id) {
		return productServiceClient.getProduct(id);
	}

}
