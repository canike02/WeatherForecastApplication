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

    /*
    * Rest/GET API to fetch weather forecast details for a zipCode (required)
    * and for (optional) a number of days in the future, using default as 16).
    */
    @GetMapping("/forecast")
    public ResponseEntity<ForecastDetails> getWeatherForecast(
            @RequestParam Long zipCode,
            @RequestParam(required = false, defaultValue = "16") String numberOfDays ){

        int daysToDisplayFor = Integer.parseInt(numberOfDays);

        ForecastDetails details =
                this.weatherForecastService.getWeatherForecast(zipCode, daysToDisplayFor);

        //when incorrect zip code details are fed in, or the service fails to fetch anything
        if(Objects.isNull(details.getWeatherForecastData().getCity_name()))
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
