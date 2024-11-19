package com.learn.journalapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl {
    
    private final JavaMailSender mailSender;

    public void sendMail(String sendTo, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("jayasimhareddy8@gmail.com");
            mail.setTo(sendTo);
            mail.setSubject(subject);
            mail.setText(body);
            mailSender.send(mail);
            log.info("Email Sent Successfully!");
        } catch(Exception e) {
            log.error("Error Sending mail: ",e);
        }
    }
}
