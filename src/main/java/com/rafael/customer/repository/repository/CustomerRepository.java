package com.rafael.customer.repository.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.customer.repository.domain.Customer;
import com.rafael.customer.repository.domain.State;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
	List<Customer> findByName(String name);
	List<Customer> findByState(State state);
}
