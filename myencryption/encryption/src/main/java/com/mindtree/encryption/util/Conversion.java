package com.mindtree.encryption.util;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.encryption.dto.CustomerDTO;
import com.mindtree.encryption.entity.Customer;



public class Conversion 
{
	/**
	 * @param <T>
	 * @param Iterable 
	 * @return List
	 */
	public static <T>List<T> convertIterableToList(Iterable<T> i)
	{
		List<T> l = new ArrayList<>();
		for (T t : i)
		{
			l.add(t);
		}
		return l;
	}
	
}
