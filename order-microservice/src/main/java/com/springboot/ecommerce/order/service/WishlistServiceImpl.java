package com.springboot.ecommerce.order.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.order.model.Product;
import com.springboot.ecommerce.order.model.Wishlist;
import com.springboot.ecommerce.order.repository.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService, ProductService {

	@Autowired
	private WishlistRepository wishlistRepo;

	@Autowired
	private ProductServiceClient productServiceClient;

	@Override
	public List<Wishlist> viewWishlist(String userId) {
		List<Wishlist> data = (List<Wishlist>) wishlistRepo.findAll();
		if (data.isEmpty()) {
			throw new EntityNotFoundException("No Wishlist found");
		} else {
			for (Wishlist result : data) {
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
	public Wishlist addWishlist(Wishlist wishlist) throws Exception {
		try {
			Wishlist data = wishlistRepo.findOneByUserIdAndProdId(wishlist.getUserId(), wishlist.getProdId());
			if (data != null) {
				throw new Exception("This product already exists");

			} else {
				Wishlist result = wishlistRepo.save(wishlist);
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
	public void deleteWishlist(String id) {
		try {
			wishlistRepo.deleteById(Long.parseLong(id));
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void deleteAllWishlist(String userId) {
		try {
			List<Wishlist> data = wishlistRepo.findByUserId(userId);
			if (data.size() > 0) {
				wishlistRepo.deleteAllByUserId(userId);
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
