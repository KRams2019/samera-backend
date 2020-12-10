package com.mindtree.encryption.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author M1056190
 *
 */
@Configuration
public class ModelMapperConfig {
	/**
	 * @return ModelMapper Instance
	 */
	@Bean
	public ModelMapper getModelMapperObject()
	{
		return new ModelMapper();
	}
}
