package com.rafael.customer.repository.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.domain.State;

@SpringBootTest
@Disabled("Disabled only test local")
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	
    @DisplayName("Test Save customer")
    @Disabled("Disabled use only load data")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}, d= {3}")
    @CsvSource({
        "1, Rafael, 200.0, Activo",
        "2, Mercedes, 500.0, Activo",
        "3, Gloria, 250.0, Activo",
        "4, Sarapoga, 10.0, Activo",
        "5, Emilio, 200.0, Inactivo"
    })
    public void saveTest(long id, String name, double creditLimit, String stateStr) {
    	Customer customer = Customer.builder()
    			.id(id)
    			.name(name)
    			.creditLimit(creditLimit)
    			.state(State.getStateEnun(stateStr))
    			.build();
    	Customer customerNew = customerRepository.save(customer);
    	Assertions.assertNotNull(customerNew);
    	Assertions.assertEquals(customer, customerNew);
    }
    
    @DisplayName("Test get customer by primary key")
    @Disabled("Disabled use only load data")
    @ParameterizedTest
    @ValueSource(longs = {0L})
    public void obtenerCustomerById(Long id) {
    	Customer customer = customerRepository.findById(id).get();
    	Assertions.assertNotNull(customer);
    }
    
    @DisplayName("Test delete customer by primary key")
    @Disabled("Disabled use only load data")
    @ParameterizedTest
    @ValueSource(longs = {0L})
    public void deleteCustomerById(Long id) {
    	customerRepository.deleteById(id);
    }
    
    @DisplayName("Test query by id")
    @ParameterizedTest
    @ValueSource(longs = {2, 4})
    public void findByIdTest(Long id) {
    	Customer customer = customerRepository.findById(id).get();
    	Assertions.assertNotNull(customer);;
    }

    @DisplayName("Test query by name")
    @ParameterizedTest
    @ValueSource(strings = {"Rafael", "Mercedes"})
    public void findByNameTest(String name) {
    	List<Customer> customers = customerRepository.findByName(name);
    	Assertions.assertEquals(1, customers.size());
    }

    @DisplayName("Test query by state")
    @ParameterizedTest
    @ValueSource(strings = {"Activo", "Inactivo"})
    public void findByStateTest(String estado) {
    	List<Customer> customers = customerRepository.findByState(State.getStateEnun(estado));
    	if (estado.equals("Activo")) {
        	Assertions.assertEquals(4, customers.size());
    	} else if (estado.equals("Inactivo")) {
        	Assertions.assertEquals(1, customers.size());
    	}
    }
}
