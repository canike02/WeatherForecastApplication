package com.apple.weather.app.service.impl;


import com.apple.weather.app.model.ForecastDay;
import com.apple.weather.app.model.ForecastDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class WeatherForecastApiService {


    Logger logger = LoggerFactory.getLogger(WeatherForecastApiService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.details.url}")
    private String weatherDetailUrl;

    @Cacheable(value = "forecast", key = "#zipCode", cacheManager="cacheManager")
    public synchronized ForecastDetails getForecastDetailsFromApi(Long zipCode, int numberOfDays) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<ForecastDay> request = new HttpEntity<>(headers);
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(weatherDetailUrl)
                .queryParam("postal_code", zipCode)
                .queryParam("days", numberOfDays)
                .queryParam("key", "6715fd8fdecd4e0db17c6deac91f7bb4");

        logger.info("fetching weather details from api ");
        ResponseEntity<ForecastDay> response = restTemplate.getForEntity(uri.toUriString(), ForecastDay.class, request);

        return Objects.nonNull(response.getBody()) ?
                new ForecastDetails(response.getBody(), Boolean.FALSE) : new ForecastDetails(new ForecastDay(), Boolean.FALSE);
    }
}
