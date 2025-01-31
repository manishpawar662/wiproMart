package com.wipro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.WiproMartApplication;
import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.ProductRepository;
import com.wipro.wipromart.service.ProductService;
import com.wipro.wipromart.service.ProductServiceImpl;

@SpringBootTest(classes = {WiproMartApplication.class})
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService = new ProductServiceImpl();
	
	@Mock
	private ProductRepository productRepository;
	
	@Test
	void testGetProducutById() {
		
		Product product = new Product();
		product.setProductId(200);
		product.setProductName("MyProduct");
		product.setProductPrice(5000);
		product.setMfd(LocalDate.of(2024, 10, 10));
		product.setCategory("dummy");
		
		Optional<Product> optionalProduct = Optional.of(product);
		
		when(productRepository.findById((int) 200L)).thenReturn(optionalProduct);
		
		Product actualProduct = productService.getProductByid(200);
		
		assertEquals("MyProduct",actualProduct.getProductName());		
		assertEquals(5000,actualProduct.getProductPrice());		
		
	}
	
	@Test
	void testGetProducutByIdWithException() {
		
		when(productRepository.findById((int) 200L)).thenThrow(ResourceNotFoundException.class);
				
		assertThrows(ResourceNotFoundException.class, ()-> productService.getProductByid(200));		
	}
	
	@Test
    void testGetAllProduct() {
    	Product product1 = new Product();
    	product1.setProductId(100);
    	product1.setProductName("Samsung s10");
    	product1.setCategory("Mobile");
    	product1.setMfd(LocalDate.of(2023, 03, 10));
    	product1.setProductPrice(21000);
    	
    	Product product2 = new Product();
    	product2.setProductId(101);
    	product2.setProductName("Asus Tuf F15");
    	product2.setCategory("Laptop");
    	product2.setMfd(LocalDate.of(2024, 11, 10));
    	product2.setProductPrice(51000);
    	
    	Product product3 = new Product();
    	product3.setProductId(102);
    	product3.setProductName("Acer Niro V");
    	product3.setCategory("Laptop");
    	product3.setMfd(LocalDate.of(2024, 01, 02));
    	product3.setProductPrice(65000);
    	
    	List<Product> optionalProduct = new ArrayList<Product>();
    	
    	optionalProduct.add(product1);
    	optionalProduct.add(product2);
    	optionalProduct.add(product3);
    	
    	when(productService.getAllProducts()).thenReturn(optionalProduct);
    	
    	List<Product> productList = productService.getAllProducts();
    	
    	assertEquals(optionalProduct.size(),productList.size());
    }
    
    @Test
    void testGetAllProductwithException() {
    	when(productService.getAllProducts()).thenThrow(ResourceNotFoundException.class);
    	
    	assertThrows(ResourceNotFoundException.class, () -> productService.getAllProducts());
    }
    
    @Test
    void testSaveProduct() {
    	Product product = new Product();
    	product.setProductId(100);
    	product.setProductName("Samsung");
    	product.setCategory("Mobile");
    	product.setProductPrice(21000);
    	product.setMfd(LocalDate.of(2024, 11, 21));
    	
    	when(productService.saveProduct(product)).thenReturn(product);
    	Product newProduct = productService.saveProduct(product);
    	
    	assertEquals(product,newProduct);
//    	assertEquals(customer.getCustomerId(), newCustomer.getCustomerId());
//    	assertEquals(customer.getFirstName(), newCustomer.getFirstName());
//    	assertEquals(customer.getLastName(), newCustomer.getLastName());
//    	assertEquals(customer.getEmail(), newCustomer.getEmail());
//    	assertEquals(customer.getMobile(), newCustomer.getMobile());
//    	assertEquals(customer.getCity(), newCustomer.getCity());
    	
    }
		
}
