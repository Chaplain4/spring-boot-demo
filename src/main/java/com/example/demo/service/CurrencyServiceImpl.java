package com.example.demo.service;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Currency;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    CurrencyRepository repository;

//    @Override
//    public List<CurrencyDto> getAllCurrencies() {
//        List<CurrencyDto> result = new ArrayList<>();
//        List<Currency> l = repository.findAll();
//        l.forEach(Currency -> {
//            CurrencyDto currencyDto =;
//        });
//    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return null;
    }

    @Override
    public List<CurrencyDto> getForceAllCurrencies() {
        return null;
    }

    @Override
    public CurrencyDto getCurrencyByCharCode(String charCode) {
        return null;
    }

    //call service
//    private List<Currency> load() {
//        return XMLCurrencyParser.getAllCurrencies();
//    }
}
