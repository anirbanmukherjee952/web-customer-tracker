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

import com.sandbox.webcustomertracker.entity.Customer;
import com.sandbox.webcustomertracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// fetch customers from DB
		List<Customer> customers = customerService.getCustomers();
		
		// add them to model
		theModel.addAttribute("customers",customers);
		
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
		
		return "redirect:/customer/list";
	
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
	
}














