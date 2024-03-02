package com.apple.weather.app;

import com.apple.weather.app.api.WeatherForecastController;
import com.apple.weather.app.config.WeatherForecastConfigurations;
import com.apple.weather.app.service.impl.WeatherForecastApiService;
import com.apple.weather.app.service.impl.WeatherForecastServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {WeatherForecastController.class, WeatherForecastServiceImpl.class, WeatherForecastConfigurations.class, WeatherForecastApiService.class})
@EnableAutoConfiguration
@EnableCaching
public class WeatherForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastApplication.class, args);
	}

}
