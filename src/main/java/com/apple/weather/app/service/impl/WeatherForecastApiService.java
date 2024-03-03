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

@Service
public class WeatherForecastApiService {

    Logger logger = LoggerFactory.getLogger(WeatherForecastApiService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.resource.hash.key}")
    private String apiResourceHashKey;

    @Value("${weather.details.url}")
    private String weatherDetailUrl;


    /*
    * service that actually fetched weather forecast data
    * from an API, using provided inputs and caches the response.
    * @return  - ForecastDetails
    * */

    @Cacheable(value = "forecast", key = "#zipCode", cacheManager="cacheManager", unless = "#result == null")
    public synchronized ForecastDetails getForecastDetailsFromApi(Long zipCode, int numberOfDays) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<ForecastDay> request = new HttpEntity<>(headers);
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(weatherDetailUrl)
                .queryParam("postal_code", zipCode)
                .queryParam("days", numberOfDays)
                .queryParam("key", apiResourceHashKey);

        logger.info("fetching weather details from api ....");
        ResponseEntity<ForecastDay> response = restTemplate.getForEntity(uri.toUriString(), ForecastDay.class, request);

        return response.hasBody() ?
                new ForecastDetails(response.getBody(), Boolean.FALSE) : null;
    }
}
