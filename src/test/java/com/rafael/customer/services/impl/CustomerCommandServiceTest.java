package com.rafael.customer.services.impl;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.domain.State;
import com.rafael.customer.repository.repository.CustomerRepository;

public class CustomerCommandServiceTest {

	@InjectMocks
	CustomerCommandServiceImpl customerCommandService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	CounterCommandServiceImpl counterCommandServiceImpl;

	@Mock
	DozerBeanMapper mapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
    	Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(resultCustomer());
    	Mockito.when(mapper.map(Mockito.any(CustomerDto.class), Mockito.eq(Customer.class))).thenReturn(resultCustomer());
    	Mockito.when(mapper.map(Mockito.any(Customer.class), Mockito.eq(CustomerDto.class))).thenReturn(resultCustomerDto());
    	Mockito.when(counterCommandServiceImpl.nextValue()).thenReturn(1L);
    	Mockito.doNothing().when(customerRepository).delete(Mockito.any(Customer.class));
	}
	
	@DisplayName("Test create customer")
    @Test
    public void createCustomerTest() {
    	
    	CustomerDto customerDto = customerCommandService.createCustomer(new CustomerDto());
    	Assertions.assertNotNull(customerDto);
    	Assertions.assertAll("customer Dto", 
    			() -> Assertions.assertEquals(new Long(1), customerDto.getId()),
    			() -> Assertions.assertEquals("Mercedes", customerDto.getName()),
    			() -> Assertions.assertEquals(new Double(200.00), customerDto.getCreditLimit()),
    			() -> Assertions.assertEquals("Activo", customerDto.getState()));

    }

    @DisplayName("Test update customer")
    @Test
    public void updateCustomerTest() {
    	CustomerDto customerDto = customerCommandService.updateCustomer(new CustomerDto());
    	Assertions.assertNotNull(customerDto);

    	Assertions.assertAll("customer Dto", 
    			() -> Assertions.assertEquals(new Long(1), customerDto.getId()),
    			() -> Assertions.assertEquals("Mercedes", customerDto.getName()),
    			() -> Assertions.assertEquals(new Double(200.00), customerDto.getCreditLimit()),
    			() -> Assertions.assertEquals("Activo", customerDto.getState()));
    }

    @DisplayName("Test delete customer")
    @Test
    public void deleteCustomerTest() {
    	customerCommandService.deleteCustomer(new CustomerDto());
    }
    
    private Customer resultCustomer() {
    	Customer customer = 
    		Customer.builder()
    			.id(1L)
    			.name("Mercedes")
    			.creditLimit(200.00)
    			.state(State.ACTIVO)
    			.build();
    	return customer;
    }

    private CustomerDto resultCustomerDto() {
    	CustomerDto customerDto = 
    		CustomerDto.builder()
    			.id(1L)
    			.name("Mercedes")
    			.creditLimit(200.00)
    			.state("Activo")
    			.build();
    	return customerDto;
    }
}
