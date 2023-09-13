package com.demo.crm.service;

import org.springframework.stereotype.Repository;

import com.demo.crm.entity.Customer;
import com.demo.crm.entity.request.CustomerRequest;
import com.demo.crm.entity.response.CustomerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICustomerService {

	Mono<Customer> findCustomerById(Long id);

	Mono<CustomerResponse> findCustomerByMobile(String mobile);

	Mono<CustomerResponse> findCustomerByEmail(String email);
	
	Flux<Customer> findAllCustomers();
	
    Mono<Customer> saveCustomer(CustomerRequest request);

    Mono<Customer> updateCustomer(CustomerRequest request);

    Mono<Boolean> deleteCustomerById(Long id);
    
    Mono<Boolean> deleteCustomerByMobile(Long mobile);

	
}
