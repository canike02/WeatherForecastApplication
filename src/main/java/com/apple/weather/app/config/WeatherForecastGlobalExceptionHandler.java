package com.apple.weather.app.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;


@ControllerAdvice
public class WeatherForecastGlobalExceptionHandler {

    @ExceptionHandler(value = {RestClientException.class, HttpClientErrorException.class})
    public ResponseEntity<ErrorResponse> handleApiResponseException(RestClientException restClientException, HttpClientErrorException httpClientErrorException) {
        return ResponseEntity.internalServerError().build();
    }
}
