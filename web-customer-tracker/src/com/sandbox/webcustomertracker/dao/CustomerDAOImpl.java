package com.sandbox.webcustomertracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sandbox.webcustomertracker.SortBy;
import com.sandbox.webcustomertracker.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int theSortField) {
		
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// determine sort field
		String theFieldName = null;
		
		if(theSortField == SortBy.firstName.ordinal()) {
			System.out.println(SortBy.firstName.toString());
			theFieldName = SortBy.firstName.toString();
		}
		else if(theSortField == SortBy.lastName.ordinal()) {
			System.out.println(SortBy.firstName.toString());
			theFieldName = SortBy.lastName.toString();
		}
		else if(theSortField == SortBy.email.ordinal()) {
			System.out.println(SortBy.firstName.toString());
			theFieldName = SortBy.email.toString();
		}
		else {
			// if nothing matches the default to sort by lastName
			theFieldName = "lastName";
		}
		
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + theFieldName, 
															Customer.class);
//		theQuery.setParameter("theFieldName", theFieldName);
		
		// execute query and get result
		List<Customer> customers = theQuery.getResultList();
		
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

	@Override
	public void deleteCustomer(int theId) {
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// fetch the customer
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		// delete the customer
		currentSession.delete(theCustomer);
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> theQuery = null;
	        
        // only search by name if theSearchName is not empty
       
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) "
						            		+ "like :theName or lower(lastName) like :theName "
						            		+ "order by lastName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer order by lastName", Customer.class);            
        }
        
		// execute query and get result
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
		
	}

}
