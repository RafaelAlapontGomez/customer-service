package com.rafael.customer.services;

import java.util.List;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.repository.domain.State;
import com.rafael.customer.services.exceptions.NoDataFoundException;

public interface CustomerQueryService {
	List<CustomerDto> findAll();
	CustomerDto findById(Long id) throws NoDataFoundException;
	List<CustomerDto> findByName(String name);
	List<CustomerDto> findByState(State state);
}
