package com.demo.crm.delegate.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.demo.crm.delegate.ICustomerDelegate;
import com.demo.crm.entity.Customer;
import com.demo.crm.entity.exception.ArgumentException;
import com.demo.crm.entity.request.CustomerRequest;
import com.demo.crm.repository.ICustomerRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerDelegate implements ICustomerDelegate {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Mono<Customer> findCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Mono<Customer> findCustomerByMobile(String mobile) {
		return customerRepository.findByMobile(mobile);
	}

	@Override
	public Mono<Customer> findCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Mono<Customer> saveCustomer(CustomerRequest request) {
		log.info("This method is used for saving customer.");
		return this.findCustomerByMobile(request.getMobile()).filter(customer -> {
			throw new ArgumentException("Customer Already Exist With Mobile : " + request.getMobile());
		}).switchIfEmpty(Mono.defer(() -> {
			Customer customer = new Customer();
			BeanUtils.copyProperties(request, customer);
			log.debug("Customer Response Ready For Save!");
			return customerRepository.save(customer);
		}));

	}

	@Override
	public Mono<Customer> updateCustomer(CustomerRequest request) {
		return this.findCustomerByMobile(request.getMobile()).map(customer -> {
			if (!ObjectUtils.isEmpty(request.getName())) {
				customer.setName(request.getName());
			}
			if (!ObjectUtils.isEmpty(request.getAddress())) {
				customer.setAddress(request.getAddress());
			}
			if (!ObjectUtils.isEmpty(request.getAge())) {
				customer.setAge(request.getAge());
			}
			return customer;
		}).flatMap(customerRepository::save);
	}

	@Override
	public Mono<Boolean> deleteCustomerById(Long id) {
		return customerRepository.findById(id).flatMap(customerRepository::delete).thenReturn(true);
	}

	@Override
	public Mono<Boolean> deleteCustomerByMobile(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

}
