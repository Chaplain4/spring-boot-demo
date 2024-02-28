package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:custom.properties")
public class WelcomeMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.body}")
    private String body;

    @Value("${spring.mail.subject}")
    private String subject;

    public void sendNewMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}