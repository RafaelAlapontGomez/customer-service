package com.rafael.customer.services.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.repository.CustomerRepository;
import com.rafael.customer.services.CustomerCommandService;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CounterCommandServiceImpl counterCommandServiceImpl;
	
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		Long id = counterCommandServiceImpl.nextValue();
		customer.setId(id);
		CustomerDto customerDtoNew = mapper.map(customerRepository.save(customer), CustomerDto.class);
		return customerDtoNew;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		CustomerDto customerDtoNew = mapper.map(customerRepository.save(customer), CustomerDto.class);
		return customerDtoNew;
	}

	@Override
	public void deleteCustomer(CustomerDto customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}



}
