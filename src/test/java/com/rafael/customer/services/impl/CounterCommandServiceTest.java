package com.rafael.customer.services.impl;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rafael.customer.repository.domain.Counter;
import com.rafael.customer.repository.repository.CounterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterCommandServiceTest {

	@InjectMocks
	CounterCommandServiceImpl counterCommandService;

	@Mock
	CounterRepository counterRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
    	Mockito.when(counterRepository.findById(Mockito.anyString())).thenReturn(returnCounter());
	}
	
    @DisplayName("Test get id and incremet")
    @Test
    public void nextValueTest() {
    	Long id = counterCommandService.nextValue();
    	log.info("id = {}", id);
    	Assertions.assertEquals(new Long(1), id);
    }

    @DisplayName("Test get id and decreiment")
    @Test
    public void beforeValueTest() {
    	Long id = counterCommandService.beforeValue();
    	log.info("id = {}", id);
    	Assertions.assertEquals(new Long(1), id);
    }

    @DisplayName("Test get id")
    @Test
    public void currentValueTest() {
    	Long id = counterCommandService.currentValue();
    	log.info("id = {}", id);
    	Assertions.assertEquals(new Long(1), id);
    }
    
    private Optional<Counter> returnCounter() {
    	Counter counter = 
    		Counter.builder()
    			.id("customer")
    			.qty(new Long(1))
    			.build();
    	return Optional.of(counter);
    }
}
