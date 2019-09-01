package com.rafael.customer.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.rafael.customer.services.exceptions.NoDataFoundException;

public class CustomerQueryServiceTest {
	@InjectMocks
	CustomerQueryServiceImpl customerQueryService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	DozerBeanMapper mapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
    	Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(resultCustomer());
    	Mockito.when(customerRepository.findAll()).thenReturn(resultCustomers());
    	Mockito.when(customerRepository.findByName(Mockito.anyString())).thenReturn(resultCustomersRafael());
    	Mockito.when(customerRepository.findByState(Mockito.any(State.class))).thenReturn(resultCustomersActivos());
    	Mockito.when(mapper.map(Mockito.any(Customer.class), Mockito.eq(CustomerDto.class))).thenReturn(resultCustomerDto());

	}
	
	@DisplayName("Test get a customer")
    @Test
    public void findByIdTest() throws NoDataFoundException {
		CustomerDto customerDto = customerQueryService.findById(0L);
    	Assertions.assertAll("customer Dto", 
    			() -> Assertions.assertEquals(new Long(1), customerDto.getId()),
    			() -> Assertions.assertEquals("Mercedes", customerDto.getName()),
    			() -> Assertions.assertEquals(new Double(200.00), customerDto.getCreditLimit()),
    			() -> Assertions.assertEquals("Activo", customerDto.getState()));
	}

	@DisplayName("Test Not found customer")
    @Test
    public void findByIdNotFoundTest() {
    	Mockito.when(customerRepository.findById(Mockito.anyLong())).thenAnswer( invocation -> { throw new NoDataFoundException("Test exception"); });
    	
    	Throwable thrown;
    	thrown = Assertions.assertThrows(NoDataFoundException.class, () -> {
    		customerRepository.findById(Mockito.anyLong());
        });
    	Assertions.assertEquals("Test exception", thrown.getMessage());	   
	}
	
	@DisplayName("Test get customer list")
    @Test
    public void findAllTest() {
		List<CustomerDto> customersDto = customerQueryService.findAll();
		Assertions.assertEquals(resultCustomers().size(), customersDto.size());
		Assertions.assertTrue(compareOrderLists(resultCustomers(), customersDto));
	}

	@DisplayName("Test get customer list by name")
    @Test
    public void findByNameTest() {
		List<CustomerDto> customersDto = customerQueryService.findByName("");
		Assertions.assertEquals(resultCustomersRafael().size(), customersDto.size());
		Assertions.assertTrue(compareOrderLists(resultCustomersRafael(), customersDto));
	}

	@DisplayName("Test get customer list by State")
    @Test
    public void findByStateTest() {
		List<CustomerDto> customersDto = customerQueryService.findByState(State.ACTIVO);
		Assertions.assertEquals(resultCustomersActivos().size(), customersDto.size());
		Assertions.assertTrue(compareOrderLists(resultCustomersActivos(), customersDto));
	}


    private Optional<Customer> resultCustomer() {
    	Customer customer = 
    		Customer.builder()
    			.id(1L)
    			.name("Mercedes")
    			.creditLimit(200.00)
    			.state(State.ACTIVO)
    			.build();
    	return Optional.of(customer);
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

    private List<Customer> resultCustomers() {
    	Customer customer1 = 
    		Customer.builder()
    			.id(1L)
    			.name("Mercedes")
    			.creditLimit(200.00)
    			.state(State.ACTIVO)
    			.build();
    	
    	Customer customer2 = 
        		Customer.builder()
        			.id(2L)
        			.name("Gloria")
        			.creditLimit(200.00)
        			.state(State.ACTIVO)
        			.build();

    	Customer customer3 = 
        		Customer.builder()
        			.id(3L)
        			.name("Juan")
        			.creditLimit(200.00)
        			.state(State.INACTIVO)
        			.build();

    	return Arrays.asList(customer1, customer2, customer3);
    }

    private List<Customer> resultCustomersActivos() {
    	List<Customer> customers = resultCustomers();
		List<Customer> customersFilter =
    			customers.stream()
    				.filter(item -> State.ACTIVO.equals(item.getState()))
    				.collect(Collectors.toList());
    	return customersFilter;
    	
    }

    private List<Customer> resultCustomersRafael() {
    	List<Customer> customers = resultCustomers();
		List<Customer> customersFilter =
    			customers.stream()
    				.filter(item -> "Mercedes".equals(item.getName()))
    				.collect(Collectors.toList());
    	return customersFilter;
    	   	
    }
    
    private static boolean compareOrderLists(List<Customer> list1, List<CustomerDto> list2) {

        return list1.stream().anyMatch(
                p -> p.getId() != null 
                     && list2.stream().anyMatch(
                            p2 -> p2.getId() != null 
                                  && p2.getId().equals(p.getId())));
    }

}
