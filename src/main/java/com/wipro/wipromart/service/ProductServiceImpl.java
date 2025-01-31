package com.wipro.wipromart.service;

import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductByid(long productId) {
        Optional<Product> optionalProduct = productRepository.findById((int)productId);
        if(optionalProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        Product product = optionalProduct.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct=productRepository.findById((int) id);
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Product Not Found");
		}
		Product product=optionalProduct.get();
		productRepository.delete(product);
		
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct=productRepository.findById((int) product.getProductId());
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Product Not Found");
		}
//		product=optionalProduct.get();
		productRepository.save(product);
		return product;
	}
}
