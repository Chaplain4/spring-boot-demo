package com.example.demo.controller;


import com.example.demo.controller.mvc.ForecastsMVCController;
import com.example.demo.model.Forecast;
import com.example.demo.service.ForecastService;
import com.example.demo.service.WeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecasts")
@SpringBootApplication
public class ForecastRestController {
    @Autowired
    private ForecastService forecastService;

    @GetMapping("/weatherhistory")
    public ResponseEntity<List<Forecast>> getAllForecasts() {
        List<Forecast> forecasts = forecastService.getAllForecasts();
        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    @PostMapping("/newforecast/{days}/{location}")
    public ResponseEntity<Forecast> getForecast(@PathVariable(name = "days") Integer days, @PathVariable(name = "location") String location) {
        ForecastsMVCController forecastsMVCController = new ForecastsMVCController();
        try {
            Forecast forecast = WeatherAPIService.getForecast(days, location);
            forecastService.createForecast(forecast);
            return new ResponseEntity<>(forecast, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteForecast(@PathVariable(name = "id") Integer id) {
        String message = String.valueOf(forecastService.deleteForecastById(id));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
