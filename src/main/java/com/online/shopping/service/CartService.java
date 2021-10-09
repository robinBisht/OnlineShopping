package com.online.shopping.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Cart;
import com.online.shopping.entity.Customer;
import com.online.shopping.entity.Product;
import com.online.shopping.repository.CartRepository;
import com.online.shopping.repository.CustomerRepository;
import com.online.shopping.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public Cart addProductToCart(Cart cart,Integer productId,Integer customerId) {
		Product product = productRepository.findById(productId).get();
		Customer customer = customerRepository.findById(customerId).get();
		cart.setProduct(product);
		cart.setCustomer(customer);
		return cartRepository.save(cart);
	}
	
	@Transactional
	public void removeProductFromCart( Integer cartId, Integer productId ) {
		Cart cart = cartRepository.findById(cartId).get();
		Product productToDelete = productRepository.findById(productId).get();
		cart.removeProduct(productToDelete);
		
	}
	@Transactional
	public void removeAllProducts(Cart cart) {
		Cart cartFromRepoitory = cartRepository.getById(cart.getCartId());
		cartFromRepoitory.setProducts(new HashSet<Product>());
		cartRepository.save(cart);
	}
	@Transactional
	public void removeCart(Integer cartId) {
		Cart cart = cartRepository.findById(cartId).get();
		Set<Product> productSet = cart.getProducts();
		for( Product product: productSet ) {
			product.getCarts().remove(cart);
		}
		cartRepository.delete(cart);
	}
	
}
