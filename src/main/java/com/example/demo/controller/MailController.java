package com.example.demo.controller;

import com.example.demo.service.MailSenderService;
import com.example.demo.service.WelcomeMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class MailController {

    @Autowired
    private MailSenderService mailService;

    @Autowired
    private WelcomeMailService wms;

    @GetMapping("/mails/{to}/{subject}/{body}")
    public ResponseEntity<String> sendMsg(@PathVariable(name = "to") String to, @PathVariable(name = "subject") String subject,
                                          @PathVariable(name = "body") String body) {
        String message = body + " / Server time: " + new Date();
        mailService.sendNewMail(to, subject, message);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/mails/{to}")
    public ResponseEntity<String> sendWelcomeMsg(@PathVariable(name = "to") String to) {

        wms.sendNewMail(to);

        return new ResponseEntity<>(HttpStatus.OK);
    }


} // String to, String subject, String body
