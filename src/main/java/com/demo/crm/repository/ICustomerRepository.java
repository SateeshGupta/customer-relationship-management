package com.demo.crm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.demo.crm.entity.Customer;

import reactor.core.publisher.Mono;

public interface ICustomerRepository extends ReactiveCrudRepository<Customer, Long> {

	Mono<Customer> findByMobile(String mobile);

	Mono<Customer> findByEmail(String Email);

	 
	
}
