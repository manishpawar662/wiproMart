package com.wipro.wipromart.controller;
//{
//    "firstName": "Tarun",
//    "lastName": "Kalal",
//    "email": "KaranAhuja23@gmail.com",
//    "city": "Hyd"
//}
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		customerService.saveCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Customer> getCustomerByid(@PathVariable("id") long id){
		Customer customer = customerService.getCustomerByid(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		
	}
	@DeleteMapping("/delelte/{id}")
    public void delecustomer(@PathVariable long id) {
    	customerService.deleteCustomer(id);
    }
    @PutMapping("/update")
	public ResponseEntity<Customer> updatecustomer(@Valid @RequestBody Customer customer){
		customer = customerService.updateCustomer(customer);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
}
