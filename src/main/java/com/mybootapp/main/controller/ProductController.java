package com.mybootapp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.exception.ResourceNotFoundException;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/*
	 PATH: /add
	 Method: POST
	 RequestBody: Product product
	 Response: product
	 PathVariable: None
	 */
	@PostMapping("/add")
	public Product postProduct(@RequestBody Product product) {
		product = productService.insert(product);
		return product;
	}
	
	@GetMapping("/all")
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Product product) {
		try {
			productService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		product.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(productService.insert(product));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			productService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
		productService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}