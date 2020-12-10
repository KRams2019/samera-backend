package com.mindtree.encryption.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.exception.CustomerServiceException;
import com.mindtree.encryption.exception.EncryptionServiceException;
import com.mindtree.encryption.exception.EncryptionUtilException;
import com.mindtree.encryption.service.EncryptionService;
import com.mindtree.encryption.util.EncryptionDecryption;

@Service
public class EncryptionServiceImpl implements  EncryptionService
{

		
	private final static Logger log = Logger.getLogger(EncryptionServiceImpl.class);
	@Override
	public CustomerDTO encryptCustomerData(CustomerDTO customerDTO) throws CustomerServiceException 
	{
			CustomerDTO encryptedCustomerDTO = null;
			
			try {
				encryptedCustomerDTO = EncryptionDecryption.encryptData(customerDTO);
			} 
			catch (EncryptionUtilException e)
			{
				log.error(e);
				throw new EncryptionServiceException(e);
			}
	
			return encryptedCustomerDTO;
	}
	@Override
	public CustomerDTO decryptCustomerData(CustomerDTO customerDTO) throws CustomerServiceException {
		CustomerDTO decryptedCustomerDTO = null;
		try {
			decryptedCustomerDTO = EncryptionDecryption.decryptData(customerDTO);
		} 
		catch (EncryptionUtilException e)
		{
			log.error(e);
			throw new EncryptionServiceException(e);
		}
		return decryptedCustomerDTO;
	}
	

}
