package com.mybootapp.main.controller;

import java.time.LocalDate;
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
import com.mybootapp.main.model.OutwardRegister;
import com.mybootapp.main.model.Returns;
import com.mybootapp.main.service.OutwardRegisterService;
import com.mybootapp.main.service.ReturnsService;

@RestController
@RequestMapping("/returns")
public class ReturnsController {
	
	@Autowired
	private ReturnsService returnsService;
	
	@Autowired
	private OutwardRegisterService outwardRegisterService;
	
	@GetMapping("getall")
	public List<Returns> getAll(){
		List<Returns> list = returnsService.getAll();
		return list;
	}
	
	@GetMapping("one/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		Returns returns;
		try {
			returns = returnsService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(returns);
	}
	
	@PostMapping("/add/{outwardRegisterId}")
	public ResponseEntity<?> addReturns(@PathVariable("outwardRegisterId") int outwardRegisterId,@RequestBody Returns returns){
		try {
			OutwardRegister outwardRegister = outwardRegisterService.getById(outwardRegisterId);
			returns.setOutwardRegister(outwardRegister);
			returns.setDateOfReturn(LocalDate.now());
			returns = returnsService.insert(returns);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid outwardRegisterId given");
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(returns);
	}
	
	@PutMapping("/update/{returnsId}/{outwardRegisterId}")
	public ResponseEntity<?> update(@PathVariable int returnId,@PathVariable int outwardRegisterId, @RequestBody Returns returns){
		try {
			returnsService.getById(returnId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		returns.setId(returnId);
		try {
			OutwardRegister outwardRegister = outwardRegisterService.getById(outwardRegisterId);
			returns.setOutwardRegister(outwardRegister);	
			returns.setDateOfReturn(LocalDate.now());
			returnsService.insert(returns);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid outwardRegisterId given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(returns);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		try {
			returnsService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		returnsService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}