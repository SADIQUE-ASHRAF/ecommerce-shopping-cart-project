package com.shopping.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Many Cart items can belong to one User.
	@ManyToOne
	private UserDtls user;

	@ManyToOne
	private Product product;

	private Integer quantity;
	
	// @Transient Field Stored in Database? No /Used only in Java (not persisted)
	@Transient
	private Double totalPrice;
	
	@Transient
	private Double totalOrderPrice;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDtls getUser() {
		return user;
	}

	public void setUser(UserDtls user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderprice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public Cart(Integer id, UserDtls user, Product product, Integer quantity, Double totalPrice, Double totalOrderPrice ) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice=totalPrice;
		this.totalOrderPrice=totalOrderPrice;
	}

	public Cart() {

	}

}
