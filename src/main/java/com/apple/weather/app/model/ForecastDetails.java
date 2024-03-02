package com.apple.weather.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForecastDetails {

    private ForecastDay weatherForecastData;
    private boolean isCachedData;
}
