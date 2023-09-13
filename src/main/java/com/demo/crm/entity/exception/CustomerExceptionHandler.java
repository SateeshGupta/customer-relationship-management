package com.demo.crm.entity.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.crm.responseapi.APIResponse;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(value = ArgumentException.class)
	public Mono<ResponseEntity<APIResponse>> argumentException(ArgumentException exception) {
		return Mono.just(
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.builder().message(exception.getMessage())
						.httpStatus(HttpStatus.BAD_REQUEST).success(false).dateTime(LocalDateTime.now()).build()));

	}

}
