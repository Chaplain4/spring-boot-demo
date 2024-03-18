package com.example.demo.controller.mvc;

import com.example.demo.model.Client;
import com.example.demo.model.Forecast;
import com.example.demo.service.ForecastService;
import com.example.demo.service.WeatherAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/forecasts")
public class ForecastsMVCController {
    Logger logger = LoggerFactory.getLogger(ClientMVCController.class);

    @Autowired
    private ForecastService fs;

    @GetMapping("/request")
    public String showForm(Model model) {
        logger.info("showForm started");
        model.addAttribute("forecasts", fs.getAllForecasts());
        logger.info("forecasts added");
        List<String> listDates = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7"); //Arrays.asList(0,1,2,3,4,5,6,7);
        model.addAttribute("listDates", listDates);
        List<String> listCities = Arrays.asList("Minsk, BLR", "Brest, BLR", "Vitebsk, BLR", "Gomel, BLR", "Grodno, BLR", "Mogilev, BLR");
        model.addAttribute("listCities", listCities);
        return "request_form";
    }

    @PostMapping("/request")
    public String submitForm(@ModelAttribute("days") String days, @ModelAttribute("location") String location) {

        try {
            Forecast forecast = WeatherAPIService.getForecast(Integer.parseInt(days),location);
            fs.createForecast(forecast);
            System.out.println(forecast);
            return "request_success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "request_fail";
    }

    @PostMapping("/delete/{forecast}")
    public String removeClient(@PathVariable("forecast") String id) {
        fs.deleteForecastById(Integer.parseInt(id));
        return "redirect:/forecasts/request";
    }
}
