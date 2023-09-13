package com.demo.crm.entity.request;

import lombok.Data;

@Data
public class CustomerRequest {

	private String name;
	private String address;
	private Integer age;
	private String mobile;
	private String email;
	private boolean registered;

}
