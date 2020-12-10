package com.mindtree.encryption.service.impl;

import org.springframework.stereotype.Service;

import com.mindtree.encryption.service.WriteService;
import com.mindtree.encryption.util.WriteExcelFile;

@Service
public class WriteServiceImpl implements WriteService
{

	@Override
	public Object writeData()
	{
		
		return WriteExcelFile.writeFile();
	}

}
