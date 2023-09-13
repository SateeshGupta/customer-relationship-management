package com.demo.crm.delegate;

import com.demo.crm.entity.Customer;
import com.demo.crm.entity.request.CustomerRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerDelegate {

	Mono<Customer> findCustomerById(Long id);

	Mono<Customer> findCustomerByMobile(String mobile);

	Mono<Customer> findCustomerByEmail(String email);

	Flux<Customer> findAllCustomers();

	Mono<Customer> saveCustomer(CustomerRequest request);

	Mono<Customer> updateCustomer(CustomerRequest request);

	Mono<Boolean> deleteCustomerById(Long id);

	Mono<Boolean> deleteCustomerByMobile(String mobile);

}
