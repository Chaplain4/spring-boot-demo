package com.example.demo.controller;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currencies")
@SpringBootApplication
public class CurrencyController {

    @Autowired
    private CurrencyService cs;

    @GetMapping("/saveall")
    public ResponseEntity<String> saveall(){
        cs.saveAllCurrencies();
        return new ResponseEntity<>("Inserted", HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<CurrencyDto>> getAll(){
        List<CurrencyDto> dtos = cs.getAllCurrencies();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/test")
    @Scheduled (fixedRate = 5000)
    public void test(){
        System.out.println("TEST");
    }

    @GetMapping("/get_by_charcode/{charcode}")
    public ResponseEntity<CurrencyDto> getAll(@PathVariable(name = "charcode") String charcode){
        List<CurrencyDto> dtos = cs.getAllCurrencies();
        List<CurrencyDto> result = new ArrayList<>();
        dtos.forEach(currencyDto -> {
            if (currencyDto.getCharCode().equalsIgnoreCase(charcode)) {
                result.add(currencyDto);
            }
        });
        if (result.isEmpty()) {
            return null;
        } else return new ResponseEntity<>(result.get(0), HttpStatus.OK);
    }

//    @GetMapping("/updateall")
//    @Modifying
//    public ResponseEntity<String> updateall(){
//        cs.saveAllCurrencies();
//        return new ResponseEntity<>("Inserted", HttpStatus.OK);
//    }
}
