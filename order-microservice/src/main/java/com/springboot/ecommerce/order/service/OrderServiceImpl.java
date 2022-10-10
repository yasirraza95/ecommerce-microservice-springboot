package com.springboot.ecommerce.order.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.order.model.Cart;
import com.springboot.ecommerce.order.model.Order;
import com.springboot.ecommerce.order.model.OrderDetail;
import com.springboot.ecommerce.order.model.Product;
import com.springboot.ecommerce.order.repository.CartRepository;
import com.springboot.ecommerce.order.repository.OrderDetailRepository;
import com.springboot.ecommerce.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService, ProductService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderDetailRepository orderDetRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductServiceClient productServiceClient;

	@Override
	public List<Order> viewOrderByUserId(String userId) {
		List<Order> data = (List<Order>) orderRepo.findByUserId(userId);
		if (data.isEmpty()) {
			throw new EntityNotFoundException("No Orders found");
		} else {
			for (Order order : data) {
				for (OrderDetail orderDet : order.getOrderDetail()) {
					Product product = this.getProdDetail(Integer.parseInt(orderDet.getProdId()));
					String prodName = product.getData().getName();
					String prodImage = product.getData().getImage();
					String price = product.getData().getPrice();

					orderDet.setProdName(prodName);
					orderDet.setProdImage(prodImage);
					orderDet.setProdPrice(price);

				}
			}

			return data;
		}
	}

	@Override
	public Order placeOrder(Order order) throws Exception {
		// TODO
		try {
			List<Cart> data = cartRepo.findByUserId(order.getUserId());

			int totalOrderPrice = 0;

			if (data.size() > 0) {

				Order result = orderRepo.save(order);
				long orderId = result.getId();
				if (orderId > 0) {

					for (Cart cart : data) {

						Product product = this.getProdDetail(Integer.parseInt(cart.getProdId()));

						String price = product.getData().getPrice();
						int totalPrice = Integer.parseInt(cart.getQuantity()) * Integer.parseInt(price);
						totalOrderPrice += totalPrice;

						OrderDetail orderDet = new OrderDetail();
						orderDet.setOrderId(String.valueOf(orderId));
						orderDet.setProdId(cart.getProdId());
						orderDet.setQuantity(cart.getQuantity());
						orderDet.setPrice(String.valueOf(price));
						orderDet.setTotalPrice(String.valueOf(totalPrice));

						orderDetRepo.save(orderDet);

						cartRepo.deleteById(cart.getId());

					}

					orderRepo.updateOrderPrice(String.valueOf(totalOrderPrice), orderId);

					Order response = orderRepo.findOneById(orderId);

					for (OrderDetail orderDet : response.getOrderDetail()) {
						Product product = this.getProdDetail(Integer.parseInt(orderDet.getProdId()));
						String prodName = product.getData().getName();
						String prodImage = product.getData().getImage();
						String price = product.getData().getPrice();

						orderDet.setProdName(prodName);
						orderDet.setProdImage(prodImage);
						orderDet.setProdPrice(price);
					}
					return response;

				} else {
					throw new Exception("Error inserting order");
				}

			} else {

				throw new EntityNotFoundException("No cart data found");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteById(String id) {
		try {
			orderRepo.deleteById(Long.parseLong(id));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Product getProdDetail(int id) {
		return productServiceClient.getProduct(id);
	}

}
