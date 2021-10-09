package com.online.shopping.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Cart;
import com.online.shopping.entity.Product;
import com.online.shopping.repository.CartRepository;
import com.online.shopping.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Cart addProductToCart(Cart cart,Product product) {
		Product productFromRepository = productRepository.getById(product.getProductId());
		cart.setProduct(productFromRepository);
		productFromRepository.setCart(cart);
		productRepository.save(productFromRepository);
		cartRepository.save(cart);
		return cart;
	}
	
	@Transactional
	public void removeProductFromCart( Cart cart, Product product ) {
		Product productFromRepository = productRepository.getById(product.getProductId());
		Cart cartFromRepsitory = cartRepository.getById(cart.getCartId());
		Set<Product> setOfProducts = cartFromRepsitory.getProducts();
		Set<Product> newSetOfProducts = new HashSet<>();
		for( Product prod : setOfProducts ) {
			if( prod.getProductId() == productFromRepository.getProductId() ) {
				continue;
			}
			newSetOfProducts.add(prod);
		}
		cartFromRepsitory.setProducts(newSetOfProducts);
		productFromRepository.setCart(null);
		cartRepository.save(cartFromRepsitory);
		productRepository.save(productFromRepository);
	}
	@Transactional
	public void removeAllProducts(Cart cart) {
		Cart cartFromRepoitory = cartRepository.getById(cart.getCartId());
		cartFromRepoitory.setProducts(new HashSet<Product>());
		cartRepository.save(cart);
	}
}
