package com.apple.weather.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDay {

    private String city_name;
    private String state_code;
    private String country_code;
    private Number lat;
    private Number lon;
    private String timezone;
    private List<Forecast> data;
}
