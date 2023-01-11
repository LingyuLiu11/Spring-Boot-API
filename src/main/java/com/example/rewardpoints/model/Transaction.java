package com.example.rewardpoints.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trans_id")
    private Long id;
    
    @Column(name="trans_amount")
    private String amounnt;
    
    @Column(name="customer_id")
    private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmounnt() {
		return Double.parseDouble(amounnt);
	}

	public void setAmounnt(String amounnt) {
		this.amounnt = amounnt;
	}

}
