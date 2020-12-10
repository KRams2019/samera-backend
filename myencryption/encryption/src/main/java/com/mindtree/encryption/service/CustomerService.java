package com.mindtree.encryption.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.entity.Customer;
import com.mindtree.encryption.exception.CustomerServiceDBException;


public interface CustomerService 
{
	
	/**
	 * @param multipartFile
	 * @return List of CustomerDTO
	 * @throws CustomerServiceDBException
	 */
	List<CustomerDTO> loadCustomerData(MultipartFile multipartFile) throws CustomerServiceDBException;
	/**
	 * @param customerId
	 * @return List of CustomerDTO
	 * @throws CustomerServiceDBException
	 */
	/**
	 * @param customerId
	 * @return List of CustomerDTO
	 * @throws CustomerServiceDBException
	 */
	CustomerDTO getCustomerData(Long customerId) throws CustomerServiceDBException;
	/**
	 * @param pattern on which customer will be searched
	 * @return CustomerDTO
	 * @throws CustomerServiceDBException 
	 */
	List<CustomerDTO> searchCustomerByPattern(String pattern) throws CustomerServiceDBException;
	/**
	 * @return List of CustomerDTO
	 */
	List<CustomerDTO> allCustomer();
}
