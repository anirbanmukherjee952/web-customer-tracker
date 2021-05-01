package com.sandbox.webcustomertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandbox.webcustomertracker.SortBy;
import com.sandbox.webcustomertracker.entity.Customer;
import com.sandbox.webcustomertracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(@RequestParam(name="sort",required=false) String sort,
								Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = null;
		
		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);			
		}
		else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortBy.lastName.ordinal());
		}
		
		// add them to model
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
		
	}
	
	@GetMapping("/show-form-for-add")
	public String showFormForAdd(Model theModel) {
		
		// create customer object
		Customer theCustomer = new Customer();
		
		// add to model
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
		
	}
	
	@PostMapping("/save-customer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer into DB
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list?page=1";
	
	}
	
	@GetMapping("/show-form-for-update")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// fetch the customer from DB
		Customer theCustomer = customerService.getCustomer(theId);
		
		// add the customer to model
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete-customer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list?page=1";
		
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
    							Model theModel) {
		
 		// search customers from the service
 		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
 		            
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        
        return "list-customers";   
        
    }
	
}














