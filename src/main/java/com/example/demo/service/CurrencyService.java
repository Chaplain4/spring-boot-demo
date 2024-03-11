package com.example.demo.service;

import com.example.demo.dto.CurrencyDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
    List<CurrencyDto> getForceAllCurrencies();
    CurrencyDto getCurrencyByCharCode(String charCode);
    void saveAllCurrencies();
}
