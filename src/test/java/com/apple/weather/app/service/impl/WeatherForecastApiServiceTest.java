package com.apple.weather.app.service.impl;

import com.apple.weather.app.model.ForecastDay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class WeatherForecastApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ForecastDay forecastDay;

    @InjectMocks
    private WeatherForecastApiService weatherForecastApiService;


    @Test
    public void testGetForecastDetailsFromApi() {
        ReflectionTestUtils.setField(weatherForecastApiService, "apiResourceHashKey", "apiResourceKey");
        ReflectionTestUtils.setField(weatherForecastApiService, "weatherDetailUrl", "http://weatherUrl.com");
        Mockito.doReturn(ResponseEntity.ok(forecastDay)).when(restTemplate).getForEntity(Mockito.anyString(), Mockito.eq(ForecastDay.class), Mockito.any(HttpEntity.class));
        Assertions.assertNotNull(weatherForecastApiService.getForecastDetailsFromApi(123456L, 16));
    }

    @Test
    public void testGetForecastDetailsFromApiWithEmptyResponse() {
        ReflectionTestUtils.setField(weatherForecastApiService, "apiResourceHashKey", "apiResourceKey");
        ReflectionTestUtils.setField(weatherForecastApiService, "weatherDetailUrl", "http://weatherUrl.com");
        Mockito.doReturn(ResponseEntity.ok(new ForecastDay())).when(restTemplate).getForEntity(Mockito.anyString(), Mockito.eq(ForecastDay.class), Mockito.any(HttpEntity.class));
        Assertions.assertNull(weatherForecastApiService.getForecastDetailsFromApi(123456L, 16).getWeatherForecastData().getCity_name());
    }


}
