package com.rafael.customer.services;

public interface CounterCommandService {
	
	static final String COUNTER_ID = "customer";

	Long nextValue();
	Long beforeValue();
	Long currentValue();
}
