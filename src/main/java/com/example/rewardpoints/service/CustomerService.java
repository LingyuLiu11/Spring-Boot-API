package com.example.rewardpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	// CREATE
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	// READ
	public List<Customer> getCustomer() {
		return customerRepository.findAll();
	}
	//SEARCH
	public Customer searchCustomer(Long customerId) {
		Optional<Customer> cust = customerRepository.findById(customerId);
		if (cust != null) {
			return cust.get();
		}
		return null;
	}

	// DELETE
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	// UPDATE
	public Customer updateCustomer(Long customerId, Customer customer) {
		Customer cus = customerRepository.findById(customerId).get();
		cus.setName(cus.getName());
		cus.setTransaction(cus.getTransaction());

		return customerRepository.save(cus);
	}
}
