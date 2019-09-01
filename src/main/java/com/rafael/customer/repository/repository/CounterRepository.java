package com.rafael.customer.repository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.customer.repository.domain.Counter;

public interface CounterRepository extends MongoRepository<Counter, String> {

}
