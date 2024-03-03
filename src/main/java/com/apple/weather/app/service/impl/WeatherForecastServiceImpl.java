package com.apple.weather.app.service.impl;

import com.apple.weather.app.model.ForecastDay;
import com.apple.weather.app.model.ForecastDetails;
import com.apple.weather.app.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    @Autowired
    private WeatherForecastApiService weatherForecastApiService;

    @Autowired
    private CacheManager cacheManager;


    /*
     * Service that does basic validation checks nd looks up the cache
     * for any non-null zipcode that was already fetched within a
     * defined time span in minutes.
     *
     * @params - zipCode, numberOfDays
     * @return ForecastDetails
     */
    @Override
    public ForecastDetails getWeatherForecast(Long zipCode, int numberOfDays) {

        if(Objects.isNull(zipCode))
            return new ForecastDetails(new ForecastDay(), Boolean.FALSE);

        Cache cachedData = this.cacheManager.getCache("forecast");
        if(Objects.nonNull(cachedData) && cachedData.get(zipCode) != null) {
            logger.info("Fetched details from cache");
            ForecastDetails cachedDetails = ((ForecastDetails) Objects.requireNonNull(cachedData.get(zipCode)).get());
            if(Objects.nonNull(cachedDetails))
                cachedDetails.setCachedData(Boolean.TRUE);
            return cachedDetails;
        }

        ForecastDetails forecastDetailsFetched =
                this.weatherForecastApiService.getForecastDetailsFromApi(zipCode, numberOfDays);
        if(Objects.isNull(forecastDetailsFetched))
            return new ForecastDetails(new ForecastDay(), Boolean.FALSE);

        return forecastDetailsFetched;
    }

}
