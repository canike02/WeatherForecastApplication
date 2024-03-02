package com.apple.weather.app.service;

import com.apple.weather.app.model.ForecastDetails;
import org.springframework.stereotype.Service;

@Service
public interface WeatherForecastService {

    ForecastDetails getWeatherForecast(Long zipCode, int numberOfDays);
}
