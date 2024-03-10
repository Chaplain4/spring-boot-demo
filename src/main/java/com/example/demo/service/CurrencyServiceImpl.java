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

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<CurrencyDto> result = new ArrayList<>();
        List<Currency> l = repository.findAll();
        l.forEach(Currency -> {
            CurrencyDto currencyDto = new CurrencyDto();
            currencyDto.setName(Currency.getName());
            currencyDto.setNumCode(Currency.getNumCode());
            currencyDto.setDailyTs(Currency.getDailyTs());
            currencyDto.setCharCode(Currency.getCharCode());
            currencyDto.setUpdatedTs(Currency.getUpdatedTs());
            currencyDto.setRate(Currency.getRate());
            currencyDto.setScale(Currency.getScale());
            currencyDto.setScaleOneRate(Currency.getScale()/Currency.getRate());
            result.add(currencyDto);
        });
        return result;
    }


    @Override
    public List<CurrencyDto> getForceAllCurrencies() {
        return null;
    }

    @Override
    public CurrencyDto getCurrencyByCharCode(String charCode) {
        List<CurrencyDto> list = getAllCurrencies();
        for (CurrencyDto currencyDto : list) {
            if (currencyDto.getCharCode().equals(charCode)) {
                return  currencyDto;
            }
        }
        return null;
    }


}
