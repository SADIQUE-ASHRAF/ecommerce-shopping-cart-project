package com.shopping.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Product;
import com.shopping.cart.entity.UserDtls;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Cart saveCart(Integer productId, Integer userId) {
		// Fetch user and product from DB
		UserDtls userDtls = userRepository.findById(userId).get();
		Product product = productRepository.findById(productId).get();

		// Check if product already exists in the user's cart
		Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);

		Cart cart = null;
		if (ObjectUtils.isEmpty(cartStatus)) {
			// If product not in cart: create new Cart item
			cart = new Cart();
			cart.setProduct(product);
			cart.setUser(userDtls);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		} else {
			// If already in cart: increase quantity
			cart = cartStatus;
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		// Save updated cart
		Cart saveCart = cartRepository.save(cart);
		return saveCart;
	}

	@Override
	public List<Cart> getCartByUser(Integer userId) {
		List<Cart> carts = cartRepository.findByUserId(userId);

		Double totalOrderPrice = 0.0;
		List<Cart> updateCarts = new ArrayList<>();
		for (Cart c : carts) {
			Double totalPrice = (c.getProduct().getDiscountPrice() * c.getQuantity());
			c.setTotalPrice(totalPrice);

			totalOrderPrice = totalOrderPrice + totalPrice;
			c.setTotalOrderprice(totalOrderPrice);
			updateCarts.add(c);
		}
		return updateCarts;
	}

	@Override
	public Integer getCountCart(Integer userId) {
		Integer countByUserId = cartRepository.countByUserId(userId);
		return countByUserId;
	}

	@Override
	public void updateQuantity(String sy, Integer cid) {
		Cart cart = cartRepository.findById(cid).get();
		int updateQuantity;

		if (sy.equalsIgnoreCase("de")) {
			updateQuantity = cart.getQuantity() - 1;

			if (updateQuantity <= 0) {
				cartRepository.delete(cart);
			} else {
				cart.setQuantity(updateQuantity);
				cartRepository.save(cart);

			}
		} else {
			updateQuantity = cart.getQuantity() + 1;
			cart.setQuantity(updateQuantity);
			cartRepository.save(cart);

		}
	}

}
