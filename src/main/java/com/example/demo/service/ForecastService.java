package com.example.demo.service;

import com.example.demo.model.Forecast;

import java.util.List;

public interface ForecastService {
    List<Forecast> getAllForecasts();
    Forecast getForecastById(int id);
    boolean deleteForecastById(int id);
    boolean saveOrUpdateForecast(Forecast forecast);
    boolean createForecast(Forecast forecast);
}
