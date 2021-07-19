package com.citronbrick.kpi.web;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class AuthErrorHandler {

	// @ResponseStatus(HttpStatus)
	@ExceptionHandler({NotAuthorizedException.class}) 
	public String handle403() {
		System.out.println("handle my 403");
		return "403";
	}

}
