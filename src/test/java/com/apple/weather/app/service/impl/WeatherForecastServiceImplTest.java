package com.apple.weather.app.service.impl;

import com.apple.weather.app.model.ForecastDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

@SpringBootTest
public class WeatherForecastServiceImplTest {

    @Mock
    private WeatherForecastApiService weatherForecastApiService;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @Mock
    private ForecastDetails forecastDetails;

    @InjectMocks
    private WeatherForecastServiceImpl weatherForecastServiceImpl;

    @Test
    public void testGetWeatherForeCastWithoutCachedData() {
        Mockito.doReturn(forecastDetails).when(weatherForecastApiService).getForecastDetailsFromApi(Mockito.anyLong(), Mockito.anyInt());
        Mockito.doReturn(cache).when(cacheManager).getCache(Mockito.anyString());
        Mockito.doReturn(null).when(cache).get(Mockito.anyLong());

        Assertions.assertNotNull(weatherForecastServiceImpl.getWeatherForecast(12345L, 16));

    }

    @Test
    public void testGetWeatherForeCastWithInvalidInput() {
        Mockito.doReturn(forecastDetails).when(weatherForecastApiService).getForecastDetailsFromApi(Mockito.anyLong(), Mockito.anyInt());
        Mockito.doReturn(cache).when(cacheManager).getCache(Mockito.anyString());
        Mockito.doReturn(null).when(cache).get(Mockito.anyLong());

        Assertions.assertNull(weatherForecastServiceImpl.getWeatherForecast(null, 16).getWeatherForecastData().getCity_name());

    }

    @Test
    public void testGetWeatherForeCastWithoutCachedDataAndEmptyApiResponse() {
        Mockito.doReturn(null).when(weatherForecastApiService).getForecastDetailsFromApi(Mockito.anyLong(), Mockito.anyInt());
        Mockito.doReturn(cache).when(cacheManager).getCache(Mockito.anyString());
        Mockito.doReturn(null).when(cache).get(Mockito.anyLong());

        Assertions.assertNull(weatherForecastServiceImpl.getWeatherForecast(123456L, 16).getWeatherForecastData().getCity_name());

    }

    @Test
    public void testGetWeatherForeCastWithCachedData() {
        Mockito.doReturn(forecastDetails).when(weatherForecastApiService).getForecastDetailsFromApi(Mockito.anyLong(), Mockito.anyInt());
        Mockito.doReturn(cache).when(cacheManager).getCache(Mockito.anyString());
        Mockito.doReturn(new SimpleValueWrapper(forecastDetails)).when(cache).get(Mockito.anyLong());

        Assertions.assertNotNull(weatherForecastServiceImpl.getWeatherForecast(12345L, 16));

    }
}
