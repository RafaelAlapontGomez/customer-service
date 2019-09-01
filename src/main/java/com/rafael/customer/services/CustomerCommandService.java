package com.rafael.customer.services;

import com.rafael.customer.dtos.CustomerDto;

public interface CustomerCommandService {
	CustomerDto createCustomer(CustomerDto customerDto);
	CustomerDto updateCustomer(CustomerDto customerDto);
	void deleteCustomer(CustomerDto customerDto);
	void deleteCustomer(Long id);
}
