package com.springboot.rest.demo.controller;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String exception) {
		super(exception);
	}

}
