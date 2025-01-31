package com.wipro.wipromart.service;

import java.util.List;
import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Product;

import jakarta.validation.Valid;

public interface CustomerService  {
	public Customer saveCustomer(Customer customer);
	public Customer getCustomerByid(long id);
	public List<Customer> getAllCustomers();
	public void deleteCustomer(long id);
	
	public Customer updateCustomer(Customer customer);
	
}
