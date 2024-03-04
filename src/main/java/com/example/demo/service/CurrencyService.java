package com.example.demo.service;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
    List<CurrencyDto> getForceAllCurrencies();
    CurrencyDto getCurrencyByCharCode(String charCode);
}
