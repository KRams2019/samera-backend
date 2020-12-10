package com.mindtree.encryption.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.encryption.entity.Customer;
import com.mindtree.encryption.exception.EncryptionUtilException;
import com.mindtree.encryption.exception.FailedToGetMultipartFileInputStreamObject;
import com.mindtree.encryption.exception.FailedToGetXssfWorkBookObject;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile 
{
	private static InputStream is;
	private static XSSFWorkbook xssfWorkbook = null;
	private static XSSFSheet xssfSheet = null;
	private static XSSFRow xssfRow = null;
	private final static String ERROR_MESSAGE = "error in util package"; 
	private final static Logger log = Logger.getLogger(ReadExcelFile.class.getName());

	private final static char Customer_Name_Column = 'G' ;
	private final static char Customer_ID_Column = 'F' ;
	
	/**
	 * @param multipartFile
	 * @return List of Customer
	 * @throws EncryptionUtilException
	 */
	public static List<Customer> getCustomerList(MultipartFile multipartFile) throws EncryptionUtilException
	{
		initXssfWorkBook(multipartFile);
		return readCustomersData();
	}

	/**
	 * @return List of Customer
	 */
	private static List<Customer> readCustomersData() {
		Map<String,String> customer = new HashMap<String,String>();
		xssfSheet = xssfWorkbook.getSheetAt(0);
		for(int i = 1 ;i<xssfSheet.getLastRowNum();i++)
		{
			String customerName=
					xssfSheet.getRow(i).getCell((int)Customer_Name_Column - 65).toString().toUpperCase();
		
			String id =	
					(int)
					Double.parseDouble(
							xssfSheet.getRow(i).getCell((int)Customer_ID_Column - 65).toString())+"";
			if(!customer.containsKey(id)) 
			{
				customer.put(id, customerName.toUpperCase());
			}
		
		}
		return convertMapToList(customer);
	}

	/**
	 * @param customer 
	 * @return List of Customer
	 */
	private static List<Customer> convertMapToList(Map<String, String> customer) {
		List<Customer> customers = new ArrayList<Customer>();
		for (Map.Entry<String, String> entry : customer.entrySet())
		{
			Customer c = new Customer();
			c.setCustomerId(entry.getKey());
			c.setCustomerName(entry.getValue());
			customers.add(c);
		}
		return customers;
	}

	/**
	 * @param multipartFile
	 * @throws EncryptionUtilException
	 */
	private static void initXssfWorkBook(MultipartFile multipartFile) throws EncryptionUtilException 
	{
		is = getInputStreamObject(multipartFile);
		xssfWorkbook = getXssfWorkbookObject(is);
		
	}

	/**
	 * @param is2
	 * @return XSSFWorkbook Instance
	 * @throws EncryptionUtilException
	 */
	private static XSSFWorkbook getXssfWorkbookObject(InputStream is2) throws EncryptionUtilException {
			XSSFWorkbook xss = null;
		try 
		{
			xss =  new XSSFWorkbook(is2);
		} 
		catch (IOException e)
		{
			log.error(ERROR_MESSAGE, e);
			throw new FailedToGetXssfWorkBookObject(e);
		}
		return xss;
	}

	/**
	 * @param multipartFile
	 * @return InputStream Instance
	 * @throws EncryptionUtilException
	 */
	private static InputStream getInputStreamObject(MultipartFile multipartFile) throws EncryptionUtilException
	{
		InputStream is = null;
		try 
		{
			is =  multipartFile.getInputStream();
		} 
		catch (IOException e)
		{
			log.error(ERROR_MESSAGE, e);
			throw new FailedToGetMultipartFileInputStreamObject(e);
		}
		
		return is;
	}
}
