package com.check.licence.AppCheckLicence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppCheckLicenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppCheckLicenceApplication.class, args);
		
	}
}
