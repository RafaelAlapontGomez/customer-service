package com.rafael.customer.dtos.converts;

import org.dozer.DozerConverter;

import com.rafael.customer.repository.domain.State;

public class StateToStringConverter extends DozerConverter<State, String> {

	public StateToStringConverter() {
		super(State.class, String.class);
	}

	@Override
	public String convertTo(State source, String destination) {
		return State.getStateDesc(source);
	}

	@Override
	public State convertFrom(String source, State destination) {
		return State.getStateEnun(source);
	}

}
