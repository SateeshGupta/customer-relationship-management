package com.demo.crm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

	@Id
	private Long id;
	private String name;
	private String address;
	private Integer age;
	private String mobile;
	private String email;
	private boolean registered;
	
}
