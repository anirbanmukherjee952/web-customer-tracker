package com.sandbox.webcustomertracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sandbox.webcustomertracker.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result
		List<Customer> customers = query.getResultList();
		
		return customers;
		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// fetch the customer
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

}
