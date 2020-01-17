package com.check.licence.AppCheckLicence.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.check.licence.AppCheckLicence.service.EmailService;

public class EmailController {

	@Autowired
	EmailService emailService;
	
	public void SendMailController() {
		emailService.sendEmail();
	}
}
