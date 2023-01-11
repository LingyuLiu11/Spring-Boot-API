package com.example.rewardpoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.Transaction;
import com.example.rewardpoints.repository.TransactionRepository;
import com.example.rewardpoints.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
    CustomerService custService;
	
	@Autowired
    TransactionRepository transRepo;
	
	@RequestMapping(value="/customers", method=RequestMethod.POST)
	public Customer createCustomer(@RequestBody Customer cust) {
	    return custService.createCustomer(cust);
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public List<Customer> readCustomers() {
	    return custService.getCustomer();
	}

	@RequestMapping(value="/customers/{customerId}", method=RequestMethod.PUT)
	public Customer readCustomers(@PathVariable(value = "customerId") Long id, @RequestBody Customer custDetails) {
	    return custService.updateCustomer(id, custDetails);
	}

	@RequestMapping(value="/customers/{customerId}", method=RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable(value = "customerId") Long id) {
		custService.deleteCustomer(id);
	}
	
	//Get reward points
	@RequestMapping(value="/customers/{customerId}", method=RequestMethod.PUT)
	public double getRewards(@PathVariable(value = "customerId") Long id) {
	    Long transId = custService.searchCustomer(id).getTransaction();
	    List<Transaction> transactions = transRepo.findAll();
	    double rewards = 0;
	    for (Transaction trans : transactions) {
	    	if (trans.getCustomerId().equals(transId)) {
	    		rewards += rewardAlgo(trans.getAmounnt());
	    	}
	    }
	    
	    return rewards;
	    
	}
	
	//reward Algorithm
	private double rewardAlgo(Double spent) {
		int roundSpent = spent.intValue();
		int times = roundSpent / 50;
		if (times < 1) {
			return 0;
		}
		double reward = 0;
		for (int i = 1; i < times; i++) {
			reward += spent - i * 50;
		}
		return reward;
	}
}
