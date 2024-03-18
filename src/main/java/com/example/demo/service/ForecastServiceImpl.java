package com.example.demo.service;

import com.example.demo.model.Forecast;
import com.example.demo.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    ForecastRepository fr;

    @Override
    public List<Forecast> getAllForecasts() {
        return fr.findAll();
    }

    @Override
    public Forecast getForecastById(int id) {
        return fr.getReferenceById(id);
    }

    @Override
    public boolean deleteForecastById(int id) {
        try {
            fr.deleteById(id);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @Override
    @Modifying
    public boolean saveOrUpdateForecast(Forecast forecast) {
        try {
            fr.saveAndFlush(forecast);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createForecast(Forecast forecast) {
        try {
            fr.save(forecast);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
}
