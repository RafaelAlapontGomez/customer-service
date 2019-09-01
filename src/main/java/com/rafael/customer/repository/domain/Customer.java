package com.rafael.customer.repository.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "customer")
public class Customer implements Serializable {
	@Id
    private Long id;
	
    @NotNull
    private String name;

    @NotNull
    private Double creditLimit;
    
    @NotNull
    private State state;
    
}
