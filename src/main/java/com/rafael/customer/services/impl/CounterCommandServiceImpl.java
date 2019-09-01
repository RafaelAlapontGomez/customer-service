package com.rafael.customer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.customer.repository.domain.Counter;
import com.rafael.customer.repository.repository.CounterRepository;
import com.rafael.customer.services.CounterCommandService;

@Service
public class CounterCommandServiceImpl implements CounterCommandService {

	@Autowired
	CounterRepository counterRepository;
	
	@Override
	public Long nextValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		counter.setQty(currentId + 1);
		counterRepository.save(counter);
		return currentId;
	}

	@Override
	public Long beforeValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		counter.setQty(currentId - 1);
		counterRepository.save(counter);
		return currentId;
	}

	@Override
	public Long currentValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		return currentId;
	}

}
