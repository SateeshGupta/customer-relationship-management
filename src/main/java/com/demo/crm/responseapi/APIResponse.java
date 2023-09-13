package com.demo.crm.responseapi;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.demo.crm.entity.Customer;
import com.demo.crm.entity.response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse {

	private String message;
	private HttpStatus httpStatus;
	private boolean success;
	private LocalDateTime dateTime ;
	
	private Customer customer;
	
	private CustomerResponse customerResponse;
	
}
