package com.demo.crm.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.crm.entity.Customer;
import com.demo.crm.entity.request.CustomerRequest;
import com.demo.crm.entity.response.CustomerResponse;
import com.demo.crm.responseapi.APIResponse;
import com.demo.crm.service.ICustomerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("crm/v1")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@PostMapping("/save")
	public Mono<ResponseEntity<APIResponse>> saveCustomer(@RequestBody CustomerRequest request) {
		return customerService.saveCustomer(request)
				.map(customer -> APIResponse.builder().customer(customer).message("Customer Saved Successfully!")
						.httpStatus(HttpStatus.CREATED).success(true).dateTime(LocalDateTime.now()).build())
				// .map(apiresponse -> ResponseEntity.status(HttpStatus.OK).body(apiresponse));
				.map(ResponseEntity.ok()::body);
	}

	@GetMapping("/findById/{id}")
	public Mono<ResponseEntity<Customer>> findCustomerById(@PathVariable Long id) {
		return customerService.findCustomerById(id).map(ResponseEntity.ok()::body);
	}

	@GetMapping("/findByMobile/{mobile}")
	public Mono<ResponseEntity<CustomerResponse>> findCustomerByMobile(@PathVariable String mobile) {
		return customerService.findCustomerByMobile(mobile).map(ResponseEntity.ok()::body);
	}

	@GetMapping("/findByEmail/{email}")
	public Mono<ResponseEntity<APIResponse>> findCustomerByEmail(@PathVariable String email) {
		return customerService.findCustomerByEmail(email)
				.map(custRespo -> APIResponse.builder().customerResponse(custRespo)
						.message("Customer Found with email : " + custRespo.getEmail()).httpStatus(HttpStatus.FOUND)
						.success(true).dateTime(LocalDateTime.now()).build())
				.map(ResponseEntity.ok()::body);

	}

	@GetMapping("/findAll")
	public Mono<ResponseEntity<List<Customer>>> findAllCustomers() {
		return customerService.findAllCustomers().collectList().map(ResponseEntity.ok()::body);
	}

	@PutMapping("/update")
	public Mono<ResponseEntity<APIResponse>> updateCustomer(@RequestBody CustomerRequest request) {
		return customerService.updateCustomer(request)
				.map(customer -> APIResponse.builder().message("Customer Updated Successfully!")
						.httpStatus(HttpStatus.CREATED).success(true).dateTime(LocalDateTime.now()).build())
				.map(ResponseEntity.ok()::body);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Boolean>> deleteCustomerById(@PathVariable Long id) {
		return customerService.deleteCustomerById(id).map(ResponseEntity.ok()::body);
	}

}
