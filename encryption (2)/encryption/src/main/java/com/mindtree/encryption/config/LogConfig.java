package com.mindtree.encryption.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig
{
	static 
	{
		PropertyConfigurator.configure("../encryption/src/main/resources/log.properties");
	}
	
}
