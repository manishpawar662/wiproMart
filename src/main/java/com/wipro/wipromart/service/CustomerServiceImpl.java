package com.wipro.wipromart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 
	
	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customer getCustomerByid(long id) {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById((int)id);
		Customer customer = optional.get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalCustomer=customerRepository.findById((int) id);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Product Not Found");
		}
		Customer customer=optionalCustomer.get();
		customerRepository.delete(customer);
		
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalcustomer=customerRepository.findById((int) customer.getCustomerId());
		if(optionalcustomer.isEmpty()) {
			throw new ResourceNotFoundException("Product Not Found");
		}
//		customer=optionalcustomer.get();
		customerRepository.save(customer);
		return customer;
	}

}
