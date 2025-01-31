package com.wipro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.WiproMartApplication;
import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.CustomerRepository;
import com.wipro.wipromart.service.CustomerService;
import com.wipro.wipromart.service.CustomerServiceImpl;

@SpringBootTest(classes = {WiproMartApplication.class})
public class CustomerServiceTest {
	@InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testGetCustomerById() {
        //sample customer
        Customer customer = new Customer();
        customer.setCustomerId(200);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setMobile("1234567890");
        customer.setCity("Mumbai");

        Optional<Customer> optionalCustomer= Optional.of(customer);
		
		when(customerRepository.findById((int) 200L)).thenReturn(optionalCustomer);
		
		Customer actualCustomer= customerService.getCustomerByid(200);

        assertEquals(customer.getCustomerId(), actualCustomer.getCustomerId());
        assertEquals(customer.getFirstName(), actualCustomer.getFirstName());
    }

    @Test
    void testGetCustomerByIdWithException() {
        // Mock repository to throw exception
        when(customerRepository.findById(100)).thenThrow(ResourceNotFoundException.class);

        // Test service method for exception
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerByid(100));
    }
    
    @Test
    void testGetAllCustomers() {
    	Customer customer1 = new Customer();
    	customer1.setCustomerId(100);
    	customer1.setFirstName("Rohan");
    	customer1.setLastName("Kittur");
    	customer1.setEmail("rohank@gmail.com");
    	customer1.setMobile("1111111111");
    	
    	Customer customer2 = new Customer();
    	customer2.setCustomerId(101);
    	customer2.setFirstName("Mayank");
    	customer2.setLastName("Mahaan");
    	customer2.setEmail("mMayank@gmail.com");
    	customer2.setMobile("2222222222");
    	
    	Customer customer3 = new Customer();
    	customer3.setCustomerId(102);
    	customer3.setFirstName("Karthik");
    	customer3.setLastName("Pattan");
    	customer3.setEmail("pattankarthik@gmail.com");
    	customer3.setMobile("3333333333");
    	
    	
    	List<Customer> optionalCustomer = new ArrayList<Customer>();
    	
    	optionalCustomer.add(customer1);
    	optionalCustomer.add(customer2);
    	optionalCustomer.add(customer3);
    	
    	
    	when(customerService.getAllCustomers()).thenReturn(optionalCustomer);
    	
    	List<Customer> customerList = customerService.getAllCustomers();
    	
    	assertEquals(optionalCustomer.size(),customerList.size());
    }
    
    @Test
    void testGetAllCustomerswithException() {
    	when(customerService.getAllCustomers()).thenThrow(ResourceNotFoundException.class);
    	
    	assertThrows(ResourceNotFoundException.class, () -> customerService.getAllCustomers());
    }
    
    @Test
    void testSaveCustomer() {
    	Customer customer = new Customer();
    	customer.setCustomerId(100);
    	customer.setFirstName("Tarun");
    	customer.setLastName("Sayu");
    	customer.setEmail("tarun@gmail.com");
    	customer.setCity("chennai");
    	customer.setMobile("2322323232");
    	
    	when(customerService.saveCustomer(customer)).thenReturn(customer);
    	Customer newCustomer = customerService.saveCustomer(customer);
    	
    	assertEquals(customer.getCustomerId(), newCustomer.getCustomerId());
    	assertEquals(customer.getFirstName(), newCustomer.getFirstName());
    	assertEquals(customer.getLastName(), newCustomer.getLastName());
    	assertEquals(customer.getEmail(), newCustomer.getEmail());
    	assertEquals(customer.getMobile(), newCustomer.getMobile());
    	assertEquals(customer.getCity(), newCustomer.getCity());
    	
    }
    
    
}
