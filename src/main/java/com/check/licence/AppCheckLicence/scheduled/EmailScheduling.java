package com.check.licence.AppCheckLicence.scheduled;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.check.licence.AppCheckLicence.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Controller
@Component
public class EmailScheduling {
	
	@Autowired
	EmailService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailScheduling.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
    }

    @Scheduled(fixedDelay = 8000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        	//emailService.run();
            //sendEmailWithAttachment();
        try {
        	
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}

}
