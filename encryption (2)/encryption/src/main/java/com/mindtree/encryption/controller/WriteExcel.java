package com.mindtree.encryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.dto.ResponseDTO;
import com.mindtree.encryption.exception.CustomerServiceException;
import com.mindtree.encryption.service.WriteService;

@RestController
public class WriteExcel 
{
	@Autowired
	WriteService writeService;
	
	@GetMapping("/write")
	public ResponseEntity<ResponseDTO> writeToExcel()
	{
		
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK))
		.body(new ResponseDTO("encrypt Customer ", 
				true,
				false,writeService.writeData()));	
	}
}
