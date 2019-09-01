package com.rafael.customer.mappings;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.customer.dtos.CustomerDto;
import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.domain.State;

@SpringBootTest
@Disabled("Disabled only for local")
public class CustomerMappingTest {

	@Autowired
	DozerBeanMapper mapper;
	
    @DisplayName("Test Convert entity to Dto")
    @Test
    public void convertEntityToDto() {
    	Customer customer = new Customer(0L, "Rafael", 200.00, State.ACTIVO);
		CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
    	
    	Assertions.assertEquals(customer.getId(),               customerDto.getId());
    	Assertions.assertEquals(customer.getName(),             customerDto.getName());
    	Assertions.assertEquals(customer.getCreditLimit(),      customerDto.getCreditLimit());
    	Assertions.assertEquals(customer.getState().getState(), customerDto.getState());
    }

    @DisplayName("Test Convert Dto to entity")
    @Test
    public void ConvertDtoToEntity() {
    	CustomerDto customerDto = new CustomerDto(0L, "Rafael", 200.00, "Activo");
    	Customer customer = mapper.map(customerDto, Customer.class);
    	
    	Assertions.assertEquals(customerDto.getId(),          customer.getId());
    	Assertions.assertEquals(customerDto.getName(),        customer.getName());
    	Assertions.assertEquals(customerDto.getCreditLimit(), customer.getCreditLimit());
    	Assertions.assertEquals(customerDto.getState(),       customer.getState().getState());
    	
    }
}
