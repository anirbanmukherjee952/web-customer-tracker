package com.sandbox.webcustomertracker.dao;

import java.util.List;

import com.sandbox.webcustomertracker.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);
	
}
