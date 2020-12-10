package com.mindtree.encryption.service;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.exception.CustomerServiceException;

public interface EncryptionService {

	/**
	 * @param customerDTO
	 * @return encrypted Customer DTO
	 * @throws CustomerServiceException
	 */
	CustomerDTO encryptCustomerData(CustomerDTO customerDTO) throws CustomerServiceException;

	/**
	 * @param customerDTO
	 * @return decrypted Customer DTO
	 * @throws CustomerServiceException
	 */
	CustomerDTO decryptCustomerData(CustomerDTO customerDTO) throws CustomerServiceException;

}
