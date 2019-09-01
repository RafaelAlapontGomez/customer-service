package com.rafael.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.services.exceptions.NoDataFoundException;
import com.rafael.customer.services.impl.CustomerQueryServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerQueryController {

	@Autowired
	CustomerQueryServiceImpl customerService;
	
	@GetMapping("/{id}")
	ResponseEntity<CustomerDto> obtenerCustomerById(
			@PathVariable(name = "id") Long id) {
		CustomerDto customerDto = null;
		try {
			customerDto = customerService.findById(id);
		} catch (NoDataFoundException ex) {
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
		return ResponseEntity.ok(customerDto);
	}

	@GetMapping
	ResponseEntity<List<CustomerDto>> obtenerAll() {
		return ResponseEntity.ok(customerService.findAll());
	}
}
