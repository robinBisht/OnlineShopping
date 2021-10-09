package com.online.shopping.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.entity.Customer;
import com.online.shopping.entity.Order;
import com.online.shopping.entity.Product;
import com.online.shopping.service.CustomerService;
import com.online.shopping.service.OrderService;
import com.online.shopping.service.ProductService;

@RestController
@RequestMapping(path = "/customer")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{cid}/orders")
	public List<Order> getOrders(@PathVariable("cid") Integer customerId) {
		return orderService.viewAllOrderByUserId(customerId);
	}
	@GetMapping("/{cid}/orders/{oid}")
	public Order getOrderById(@PathVariable("oid") Integer orderId) {
		return orderService.viewOrderById(orderId);
	}
	@Transactional
	@PostMapping("/{cid}/orders/create/{pid}")
	public ResponseEntity<Order> createNew(@RequestBody Order order,@PathVariable("cid") Integer customerId, @PathVariable("pid") Integer productId ) {
		Customer customer = customerService.viewCustomer(customerId);
		Product product = productService.viewProduct(productId);
		order.setAddress(customer.getAddress());
		order.setCustomer(customer);
		order.setProduct(product);
		customerService.updateCustomer(customer);
		productService.updateProduct(product);
		orderService.addOrder(order);
		return ResponseEntity.ok(order);
	}
	@Transactional
	@PutMapping("/{cid}/orders/{oid}/update")
	public ResponseEntity<Order> update(@RequestBody Order order,@PathVariable("cid") Integer customerId, @PathVariable("oid") Integer orderId ) {
		order.setOrderId(orderId);
		orderService.updateOrder(order);
		return ResponseEntity.ok(order);
	}
	@DeleteMapping("/{cid}/orders/{oid}/delete")
	public ResponseEntity delete(@PathVariable("oid") Integer orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
