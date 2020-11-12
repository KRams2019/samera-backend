package com.mindtree.encryption.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.encryption.controller.CustomerController;
import com.mindtree.encryption.dto.ResponseDTO;
import com.mindtree.encryption.exception.CustomerServiceException;

@RestControllerAdvice(assignableTypes = {CustomerController.class})
public class CustomerControllerExceptionHandler 
{
	@ExceptionHandler({CustomerServiceException.class})
	public ResponseEntity<ResponseDTO> serviceExceptionHandler(CustomerServiceException e)
	{
		
	return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getLocalizedMessage().toString(), false, true,null),
			HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ResponseDTO> exceptionHandler(Exception e)
	{
		
	return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getLocalizedMessage().toString(), false, true,null),
			HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
}
