package com.mindtree.encryption.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.dto.ResponseDTO;
import com.mindtree.encryption.exception.CustomerServiceException;
import com.mindtree.encryption.service.EncryptionService;

/**
 * @author M1056190
 *
 */
@RestController
public class EncryptDecryptController 
{
	@Autowired
	private EncryptionService encryptionService;
	
	private final static Logger log  = Logger.getLogger(EncryptDecryptController.class);
	
	/**
	 * @param customerDTO
	 * @return ResponseDTO
	 * @throws CustomerServiceException
	 */
	@PostMapping("encrypt/data")
	public ResponseEntity<ResponseDTO> customerEncrypt(@RequestBody CustomerDTO customerDTO) throws CustomerServiceException
	{
		log.info("encrypt the customer data");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK))
		.body(new ResponseDTO("encrypt Customer ", 
				true,
				false,
				encryptionService.encryptCustomerData(customerDTO)));	
	}
	/**
	 * @param customerDTO
	 * @return ResponseDTO
	 * @throws CustomerServiceException
	 */
	@PostMapping("decrypt/data")
	public ResponseEntity<ResponseDTO> customerDecrypt(@RequestBody CustomerDTO customerDTO) throws CustomerServiceException
	{
		log.info("decrypt the customer data");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK))
		.body(new ResponseDTO("decrypt Customer ", 
				true,
				false,
				encryptionService.decryptCustomerData(customerDTO)));	
	}
}
