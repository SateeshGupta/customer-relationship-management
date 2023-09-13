package com.demo.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.crm.delegate.ICustomerDelegate;
import com.demo.crm.entity.Customer;
import com.demo.crm.entity.exception.ArgumentException;
import com.demo.crm.entity.request.CustomerRequest;
import com.demo.crm.entity.response.CustomerResponse;
import com.demo.crm.service.ICustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerDelegate customerDelegate;

	@Override
	public Mono<Customer> findCustomerById(Long id) {
		return customerDelegate.findCustomerById(id)
				.switchIfEmpty(Mono.error(new ArgumentException("Customer Doesn't Exist with this id : " + id)));
	}

	@Override
	public Mono<CustomerResponse> findCustomerByMobile(String mobile) {
		return customerDelegate.findCustomerByMobile(mobile).map(customer -> {
			CustomerResponse customerResponse = new CustomerResponse();
			BeanUtils.copyProperties(customer, customerResponse);
			return customerResponse;
		}).switchIfEmpty(Mono.error(new ArgumentException("Customer Doesn't Exist with this Mobile : " + mobile)));

	}

	@Override
	public Mono<CustomerResponse> findCustomerByEmail(String email) {
		return customerDelegate.findCustomerByEmail(email).map(customer -> {
			CustomerResponse customerResponse = new CustomerResponse();
			BeanUtils.copyProperties(customer, customerResponse);
			return customerResponse;
		}).switchIfEmpty(Mono.error(new ArgumentException("Customer Doesn't Exist with this Email : " + email)));

	}

	@Override
	public Flux<Customer> findAllCustomers() {
		return customerDelegate.findAllCustomers()
				.switchIfEmpty(Mono.error(new ArgumentException("NO DATA AVAILABLE IN CUSTOMER TABLE")));
	}

	@Override
	public Mono<Customer> saveCustomer(CustomerRequest request) {
		return customerDelegate.saveCustomer(request);
	}

	@Override
	public Mono<Customer> updateCustomer(CustomerRequest request) {
		return customerDelegate.findCustomerByMobile(request.getMobile()).switchIfEmpty(
				Mono.error(new ArgumentException("Customer Not Exist With This Mobile : " + request.getMobile())));
	}

	@Override
	public Mono<Boolean> deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		return customerDelegate.findCustomerById(id)
				.switchIfEmpty(Mono
						.error(new ArgumentException("Customer Not Found for delete customer with given id :: " + id)))
				.flatMap(customer -> customerDelegate.deleteCustomerById(id));
	}

	@Override
	public Mono<Boolean> deleteCustomerByMobile(Long mobile) {
		// TODO Auto-generated method stub
		return null;
	}

}
