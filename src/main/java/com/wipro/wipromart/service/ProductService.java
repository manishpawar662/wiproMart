package com.wipro.wipromart.service;

import java.util.List;

import com.wipro.wipromart.entity.Product;

public interface ProductService{
	public Product saveProduct(Product product);
	public Product getProductByid(long id);
	public List<Product> getAllProducts();
	
	Product updateProduct(Product product);
	
	public void deleteProduct(long id);
}
