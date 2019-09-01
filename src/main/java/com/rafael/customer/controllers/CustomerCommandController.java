package com.rafael.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.services.impl.CustomerCommandServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerCommandController {

	@Autowired
	CustomerCommandServiceImpl customerService;
	
	@PostMapping
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
		
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(
			@PathVariable(name = "id") Long id,
			@RequestBody CustomerDto customerDto) {
		
		return ResponseEntity.ok(customerService.updateCustomer(customerDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerDto> deleteCustomer(
			@PathVariable(name = "id") Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok(null);
	}
}
