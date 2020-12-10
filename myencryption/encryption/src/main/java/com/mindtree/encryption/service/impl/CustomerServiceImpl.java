package com.mindtree.encryption.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.entity.Customer;
import com.mindtree.encryption.exception.CustomerNotFoundException;
import com.mindtree.encryption.exception.CustomerServiceDBException;
import com.mindtree.encryption.exception.EmptyPhraseException;
import com.mindtree.encryption.exception.EncryptionUtilException;

import com.mindtree.encryption.exception.FailedToReadExcelFileException;
import com.mindtree.encryption.repo.CustomerRepo;
import com.mindtree.encryption.service.CustomerService;
import com.mindtree.encryption.util.Conversion;
import com.mindtree.encryption.util.ReadExcelFile;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ModelMapper modelMapper;
	private final static Logger log = Logger.getLogger(CustomerServiceImpl.class);
	
	@Override
	public List<CustomerDTO> loadCustomerData(MultipartFile multipartFile) throws CustomerServiceDBException {
		List<Customer> customers = null; 
		try
		{
			customers =  ReadExcelFile.getCustomerList(multipartFile);
		}
		catch (EncryptionUtilException e)
		{
			log.error("EncryptionUtilException", e);
			throw new FailedToReadExcelFileException(e);
		}
		
		customerRepo.deleteAll();				
		customers = Conversion.convertIterableToList(customerRepo.saveAll(customers)); 
		log.info("total no of customer present : "+customers.size());
		return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	@Override
	public CustomerDTO getCustomerData(Long customerId) throws CustomerServiceDBException
	{
		Optional<Customer> optionalCustomer = customerRepo.findById(customerId+"");
		optionalCustomer.orElseThrow(()->new CustomerNotFoundException("Failed To Find Customer Id : "+ customerId));
		return convertToDTO(optionalCustomer.get());
	}

	private CustomerDTO convertToDTO(Customer customer) 
	{	
		return 	modelMapper.map(customer,CustomerDTO.class);
	}
	@Override
	public List<CustomerDTO> searchCustomerByPattern(String pattern) throws CustomerServiceDBException 
	{
		MatchPhrasePrefixQueryBuilder searchByName = QueryBuilders.matchPhrasePrefixQuery("customerName",pattern.trim());
		List<Customer> customers = Conversion.convertIterableToList(customerRepo.search(searchByName));
		log.info("Size of customer list : "+customers.size());
		if(customers.size()==0)
		{
			log.error("Blank String Found");
			throw new EmptyPhraseException("Blank String Found"); 
		}
		return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	@Override
	public List<CustomerDTO> allCustomer()
	{	
		List<Customer> customers = Conversion.convertIterableToList(customerRepo.findAll());
		return	customers.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	

}
