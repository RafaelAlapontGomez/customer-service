package com.rafael.customer.repository.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.customer.repository.domain.Counter;

@SpringBootTest
@Disabled("Disabled only for local")
public class CountyRepositoryTest {

	@Autowired
	private CounterRepository counterRepository;
    
    @DisplayName("Test get counter by primary key")
    @ParameterizedTest
    @ValueSource(strings = {"customer"})
    public void getCounterByIdTest(String id) {
    	counterRepository.findById(id);
    }

    @DisplayName("Test update counter")
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
        "customer, 6"
    })
    public void updateCounterByIdTest(String id, long qty) {
    	Counter counter = Counter.builder().id(id).qty(qty).build();
    	counterRepository.save(counter);
    }


}
