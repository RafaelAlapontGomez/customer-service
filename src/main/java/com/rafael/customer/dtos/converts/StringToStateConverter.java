package com.rafael.customer.dtos.converts;

import org.dozer.DozerConverter;

import com.rafael.customer.repository.domain.State;

public class StringToStateConverter extends DozerConverter<String, State> {
	
	public StringToStateConverter() {
		super(String.class, State.class);
	}

	@Override
	public State convertTo(String source, State destination) {
		return State.getStateEnun(source);
	}

	@Override
	public String convertFrom(State source, String destination) {
		return State.getStateDesc(source);
	}
}
