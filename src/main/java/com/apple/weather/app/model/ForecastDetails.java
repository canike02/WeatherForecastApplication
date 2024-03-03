package com.apple.weather.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForecastDetails {

    private ForecastDay weatherForecastData;
    @JsonProperty(index = 1)
    private boolean isCachedData;
}
