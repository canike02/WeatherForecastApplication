package com.apple.weather.app.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@EnableEncryptableProperties
public class WeatherForecastConfigurations {

    @Value("${cache.timeout.interval.in.millis}")
    private Long cacheTimeoutInMillis;

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(cacheTimeoutInMillis, TimeUnit.MILLISECONDS);
    }
}
