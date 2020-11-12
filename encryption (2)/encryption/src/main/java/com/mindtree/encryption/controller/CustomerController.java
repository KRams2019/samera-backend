package com.mindtree.encryption.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.encryption.dto.ResponseDTO;
import com.mindtree.encryption.exception.CustomerServiceException;
import com.mindtree.encryption.service.CustomerService;
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	private final static Logger log  = Logger.getLogger(CustomerController.class);
	
	
	@GetMapping("/")
	public String home() throws CustomerServiceException
	{
		return "<h1>home</h1>";
	}
		
	/**
	 * @param multipartFile
	 * @return
	 * @throws CustomerServiceException
	 */
	@PostMapping("/encryption-elastic-search/sheet-excel")
	public ResponseEntity<ResponseDTO> 
	read(@RequestParam("file")MultipartFile multipartFile) throws CustomerServiceException
	{
		log.info("reading customer data from excel file");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.CREATED))
		.body(new ResponseDTO("file read successfully", 
				true,
				false,
				customerService.loadCustomerData(multipartFile)));	
	}
	/**
	 * @param customerId
	 * @return   ResponseDTO
	 * @throws CustomerServiceException
	 */
	@GetMapping("/encryption-elastic-search/get-customer/{customerId}")
	public ResponseEntity<ResponseDTO> getCustomer(@PathVariable Long customerId) throws CustomerServiceException {
		log.info("get customer data by customer id");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.FOUND))
		.body(new ResponseDTO("Retrieved Customer By Id", 
				true,
				false,
				customerService.getCustomerData(customerId)));	
	}
	/**
	 * @param pattern
	 * @return ResponseDto
	 * @throws CustomerServiceException
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/encryption-elastic-search/phrase-pattern-search/{pattern}")
	public ResponseEntity<ResponseDTO> getCustomerByPattern(@PathVariable String pattern) throws CustomerServiceException
	{
		log.info("auto completion");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.FOUND))
		.body(new ResponseDTO("Retrieved Customer By Phrase Search", 
				true,
				false,
				customerService.searchCustomerByPattern(pattern)));	
	}
	/**
	 * @return all Customer details
	 * @throws CustomerServiceException
	 */
	@GetMapping("/encryption-elastic-search/all-customer")
	public ResponseEntity<ResponseDTO> getAllCustomer() throws CustomerServiceException
	{
		log.info("get ");
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.FOUND))
		.body(new ResponseDTO("get all Customer ", 
				true,
				false,
				customerService.allCustomer()));	
	}

}
