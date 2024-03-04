package com.example.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CurrencyDto {
    private Integer numCode;
    private String charCode;
    private Integer scale;
    private String name;
    private Double rate;
    private Double scaleOneRate;
    private Timestamp dailyTs;
    private Timestamp updatedTs;
}