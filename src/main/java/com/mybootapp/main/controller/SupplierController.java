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
import com.mybootapp.main.model.Supplier;
import com.mybootapp.main.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

	@PostMapping("/add")
	public Supplier postSupplier(@RequestBody Supplier supplier) {
		return supplierService.insert(supplier);
	}
	
	@GetMapping("/all")
	public List<Supplier> getAll() {
		return supplierService.getAll();
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(supplierService.getById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Supplier supplier) {
		try {
			supplierService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		supplier.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(supplierService.insert(supplier));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			supplierService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
		supplierService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
