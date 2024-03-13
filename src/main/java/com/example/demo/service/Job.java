package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class Job {
    @Autowired
    private CurrencyService currencyService;


  //  @Scheduled(fixedRate = 5000)
    public void test(){
        currencyService.saveAllCurrencies();
    }
}
