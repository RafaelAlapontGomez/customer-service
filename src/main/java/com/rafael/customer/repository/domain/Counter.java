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
@Document(collection = "cuonter")
public class Counter implements Serializable {
	
	@Id
    @NotNull
    private String id;
	
	@NotNull
	private Long qty;

}
