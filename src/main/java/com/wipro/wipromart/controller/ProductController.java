//{
//    "productName": "Asus Vivobook 15",
//    "productPrice": 53500.0,
//    "mfd": "2024-04-28",
//    "category": "Laptop"
//}
package com.wipro.wipromart.controller;

import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.repository.ProductRepository;
import com.wipro.wipromart.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> fetchProductById(@PathVariable("productId") long productId) {
        Product product = productService.getProductByid(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/get/all")
    public List<Product> fetchAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }
    @DeleteMapping("/delelte/{id}")
    public void deleproduct(@PathVariable long id) {
    	productService.deleteProduct(id);
    }
    @PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product){
		product = productService.updateProduct(product);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
}
