package com.online.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Customer;
import com.online.shopping.entity.Order;
import com.online.shopping.entity.Product;
import com.online.shopping.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}
	public void removeOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).get();
		Set<Product> productSet = order.getProducts();
		for( Product product: productSet ) {
			product.getOrders().remove(order);
		}
		orderRepository.delete(order);
	}
	public List<Order> viewAllOrder(){
		return orderRepository.findAll();
	}
	@Transactional
	public Order viewOrderById(Integer orderId) {
		return orderRepository.findById(orderId).get();
	}
	public List<Order> viewAllOrdersByLocation(String location){
		List<Order> listAllOrders = viewAllOrder();
		List<Order> filterByLocation = new ArrayList<Order>();
		for( Order order: listAllOrders ) {
			if( order.getAddress().getCity().equals(location) ) {
				filterByLocation.add(order);
			}
			else if(  order.getAddress().getState().equals(location) ) {
				filterByLocation.add(order);
			}
			else if( order.getAddress().getCountry().equals(location) ) {
				filterByLocation.add(order);
			}
			else if( order.getAddress().getPincode().equals(location) ) {
				filterByLocation.add(order);
			}
		}
		return filterByLocation;
	}
	
	public List<Order> viewAllOrderByUserId(Integer customerId){
		List<Order> listAllOrders = viewAllOrder();
		return listAllOrders.stream().filter( order -> order.getCustomer().getCustomerId() == customerId ).collect(Collectors.toList());
	}
	
}
