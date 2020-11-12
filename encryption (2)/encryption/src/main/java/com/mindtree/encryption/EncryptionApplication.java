package com.mindtree.encryption;

import com.mindtree.encryption.service.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author M1056190
 *
 */
@SpringBootApplication
public class EncryptionApplication implements CommandLineRunner {
	@Resource
	ImageService imageService;
	/**
	 * @param Array of String 
	 */
	public static void main(String[] args) {
		SpringApplication.run(EncryptionApplication.class, args);
		
	}

	@Override
	public void run(String... arg) throws Exception {
imageService.deleteAll();
		imageService.init();
	}

}
