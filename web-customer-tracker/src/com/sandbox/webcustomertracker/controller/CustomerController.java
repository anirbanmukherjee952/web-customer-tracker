package com.sandbox.webcustomertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.webcustomertracker.entity.Customer;
import com.sandbox.webcustomertracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> customers = customerService.getCustomers();
		
		theModel.addAttribute("customers",customers);
		
		return "list-customers";
		
	}
	
	@GetMapping("/show-form-for-add")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
		
	}
	
	@PostMapping("/save-customer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
//		System.out.println(theCustomer);
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	
	}
	
}
