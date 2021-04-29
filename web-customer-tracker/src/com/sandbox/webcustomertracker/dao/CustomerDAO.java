package com.sandbox.webcustomertracker.dao;

import java.util.List;

import com.sandbox.webcustomertracker.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
	
}
