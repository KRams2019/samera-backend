package com.mindtree.encryption.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.exception.EncryptionUtilException;
import com.mindtree.encryption.exception.FailedToEncryptDataException;
import com.mindtree.encryption.exception.UtilInvalidKeyException;
import com.mindtree.encryption.exception.UtilNoSuchAlgorithmException;
import com.mindtree.encryption.exception.UtilPaddingException;
import com.mindtree.encryption.exception.UtilUnsupportedEncodingException;

import io.swagger.annotations.Info;


public class EncryptionDecryption {


	private final static String HASHING_ALGORITHM = "SHA-1";
	private final static String CHARACTER_ENCODING = "UTF-8";
	private final static String ENCRYPTION_ALGORITHM = "AES";
	private final static String CIPHER_INSTANCE = "AES/ECB/PKCS5Padding";
	private final static String SECRET_KEY = "qwertyuiop1234567890asdfghjklzxcvbnm";
	private final static Logger log = Logger.getLogger(EncryptionDecryption.class);
	
	private static SecretKeySpec secretKeySpec;
	private static byte[] byteKey;
	
	public static String getAlphaNumericString() 
    { 
		
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
		
    }
	
	public static String encryptData(String encry) throws EncryptionUtilException
	{
		setKey(SECRET_KEY);
		encry =   encrypt(encry);
		encry = replaceAll(encry);
		return encry;
	}
	private static String replaceAll(String encry) {
		String encry_ = "";
		for(int i =0 ;i <encry.length();i++)
		{
			if(encry.charAt(i)=='/'||encry.charAt(i)=='\\'||encry.charAt(i)=='=')
			{
					encry_ = encry_+"y";	
			}
			else
			{
				encry_ = encry_+encry.charAt(i);
			}
		}
		return encry_.trim();
	}

	public static String decryptData(String encry) throws EncryptionUtilException
	{
		setKey(SECRET_KEY);
		return decrypt(encry);
	}
	/**
	 * @param customerDTO
	 * @return encrypted CustomerDTO
	 * @throws EncryptionUtilException
	 */
	public static CustomerDTO encryptData(CustomerDTO customerDTO) throws EncryptionUtilException
	{
		setKey(SECRET_KEY);
		customerDTO.setCustomerName(encrypt(customerDTO.getCustomerName()));
		customerDTO.setCustomerId(encrypt(customerDTO.getCustomerId()));
		return customerDTO;
	}
	/**
	 * @param customerDTO
	 * @return decrypted CustomerDTO
	 * @throws EncryptionUtilException
	 */
	public static CustomerDTO decryptData(CustomerDTO customerDTO) throws EncryptionUtilException
	{
		setKey(SECRET_KEY);
		customerDTO.setCustomerName(decrypt(customerDTO.getCustomerName()));
		customerDTO.setCustomerId(decrypt(customerDTO.getCustomerId()));
		return customerDTO;
	}
	/**
	 * @param dataToDecrypt
	 * @return decoded string 
	 * @throws EncryptionUtilException
	 */
	private static String decrypt(String dataToDecrypt) throws EncryptionUtilException {
		Cipher cipher = null;
		try
		{
			cipher = Cipher.getInstance(CIPHER_INSTANCE);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		}
		catch (NoSuchAlgorithmException e)
		{
			log.error(e);
			throw new UtilNoSuchAlgorithmException(e);
		} 
		catch (NoSuchPaddingException e)
		{
			log.error(e);
			throw new UtilPaddingException(e);
			
		} 
		catch (InvalidKeyException e)
		{
			log.error(e);
			throw new UtilInvalidKeyException(e);
		}
		finally {
			if(cipher!=null)
			{
				try 
				{
					dataToDecrypt = new String(
							cipher.doFinal(
									Base64.getDecoder().decode(dataToDecrypt)
									)
							);
				}
				catch (IllegalBlockSizeException | BadPaddingException  e) 
				{
					throw new FailedToEncryptDataException(e);
				}
				
			}
		}
		return dataToDecrypt;
	}
	/**
	 * @param dataToEncrypt
	 * @return encrypted string
	 * @throws EncryptionUtilException
	 */
	private static String encrypt(String dataToEncrypt) throws EncryptionUtilException {
		Cipher cipher = null;
		try
		{
			cipher = Cipher.getInstance(CIPHER_INSTANCE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		}
		catch (NoSuchAlgorithmException e)
		{
			log.error(e);
			throw new UtilNoSuchAlgorithmException(e);
		} 
		catch (NoSuchPaddingException e)
		{
			log.error(e);
			throw new UtilPaddingException(e);
			
		} 
		catch (InvalidKeyException e)
		{
			log.error(e);
			throw new UtilInvalidKeyException(e);
		}
		
			if(cipher!=null)
			{
				try 
				{
					dataToEncrypt =  Base64.getEncoder().encodeToString(cipher.doFinal(dataToEncrypt.getBytes(CHARACTER_ENCODING)));
				}
				catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) 
				{
					throw new FailedToEncryptDataException(e);
				}
				
			}
		
		return dataToEncrypt;
	}
	/**
	 * @param secretKey
	 * @throws EncryptionUtilException
	 */
	private static void setKey(String secretKey) throws EncryptionUtilException 
	{
		MessageDigest messageDigest = null;
		
		try
		{
			byteKey = secretKey.getBytes(CHARACTER_ENCODING);
			messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
			byteKey = messageDigest.digest(byteKey);
			byteKey = Arrays.copyOf(byteKey, 16);
			secretKeySpec = new SecretKeySpec(byteKey, ENCRYPTION_ALGORITHM);

		}
		catch (NoSuchAlgorithmException e)
		{
			log.error(e);
			throw new UtilNoSuchAlgorithmException(e);
		} 
		catch (UnsupportedEncodingException e)
		{
			log.error(e);
			throw new UtilUnsupportedEncodingException(e);
		}
	}
	

}
