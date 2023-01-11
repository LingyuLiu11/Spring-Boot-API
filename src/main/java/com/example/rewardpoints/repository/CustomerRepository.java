package com.example.rewardpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rewardpoints.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
