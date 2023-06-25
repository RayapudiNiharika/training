package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybootapp.main.exception.ResourceNotFoundException;
import com.mybootapp.main.model.Returns;
import com.mybootapp.main.repository.ReturnsRepository;

@Service
public class ReturnsService {

	@Autowired
	private ReturnsRepository returnsRepository;
	
	public Returns insert(Returns returns) {
		return returnsRepository.save(returns);
	}
	
	public List<Returns> getAll() {
		return returnsRepository.findAll();
	}

	public Returns getById(int id) throws ResourceNotFoundException{
		Optional<Returns> returns = returnsRepository.findById(id);
		if(returns.isEmpty()) {
			throw new ResourceNotFoundException("invalid id given");
		}
		return returns.get();
	}

	public void delete(int id) {
		
		returnsRepository.deleteById(id);
	}

}