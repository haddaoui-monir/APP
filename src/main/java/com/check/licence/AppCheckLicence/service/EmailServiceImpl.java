package com.check.licence.AppCheckLicence.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.check.licence.AppCheckLicence.Licence.LicenceHardcodeService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private LicenceHardcodeService licenceService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
    public void run() {

        System.out.println("Sending Email...");

        try {
            sendEmail();
            //sendEmailWithAttachment()
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        System.out.println("Done");

    }
	
	
	public void sendEmail() {
		if(licenceService.ExpirationLicence().length() > 2) {
        logger.info("Fixed Delay Task :: Execution Time - {}");

       SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("phone030419@gmail.com");

        msg.setSubject("Licence Notification");
        msg.setText("Hello this Licence Will be expared SOON!! See You \n"+licenceService.ExpirationLicence());

        javaMailSender.send(msg);
		}
    }
	
}
