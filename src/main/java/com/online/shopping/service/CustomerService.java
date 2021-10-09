package com.online.shopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Customer;
import com.online.shopping.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> viewAllCustomers(){
		return customerRepository.findAll();
	}
	@Transactional
	public Customer viewCustomer(Integer id) {
		return customerRepository.findById(id).get();
	}
	public void removeCustomer(Customer customer) {
		customerRepository.delete(customer);
	}
	public void removeCustomerById(Integer customerId) {
		customerRepository.deleteById(customerId);;
	}
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
}
