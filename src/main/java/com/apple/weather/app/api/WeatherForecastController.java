package com.apple.weather.app.api;

import com.apple.weather.app.model.ForecastDetails;
import com.apple.weather.app.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/forecast")
    public ResponseEntity<ForecastDetails> getWeatherForecast(
            @RequestParam Long zipCode,
            @RequestParam(required = false) String numberOfDays ){
        int daysToDisplayFor = Objects.nonNull(numberOfDays) ? Integer.parseInt(numberOfDays) : 16;
        return new ResponseEntity<>(
                weatherForecastService.getWeatherForecast(zipCode, daysToDisplayFor),
                HttpStatus.OK);
    }
}
