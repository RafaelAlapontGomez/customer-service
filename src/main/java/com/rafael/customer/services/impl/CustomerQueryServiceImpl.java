package com.rafael.customer.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.domain.State;
import com.rafael.customer.repository.repository.CustomerRepository;
import com.rafael.customer.services.CustomerQueryService;
import com.rafael.customer.services.exceptions.NoDataFoundException;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public List<CustomerDto> findAll() {
		List<Customer> customers = customerRepository.findAll();
		return mapToCustomers(customers);
	}

	@Override
	public CustomerDto findById(Long id) throws NoDataFoundException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new NoDataFoundException(String.format("Customer %d not found", id)));
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	public List<CustomerDto> findByName(String name) {
		List<Customer> customers = customerRepository.findByName(name);
		return mapToCustomers(customers);
	}

	@Override
	public List<CustomerDto> findByState(State state) {
		List<Customer> customers = customerRepository.findByState(state);
		return mapToCustomers(customers);
	}
	
	private List<CustomerDto> mapToCustomers(List<Customer> customers) {
		List<CustomerDto> customersDto = new ArrayList<>();
		customers.forEach(item -> customersDto.add(mapper.map(item, CustomerDto.class)));
		return customersDto;
		
	}

}
