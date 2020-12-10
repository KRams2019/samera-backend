package com.mindtree.encryption.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	private static final String SWAGGER_API_VERSION = "2.0";
	private static final String title = "ENCRYPTION REST API";
	private static final String description = "RESTful API for Encryption";
	private static final String LICENSE_TEXT = "License";

	
	/**
	 * @return Docket Instance
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mindtree.encryption"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.license(LICENSE_TEXT)
				.version(SWAGGER_API_VERSION).build();
	}
}