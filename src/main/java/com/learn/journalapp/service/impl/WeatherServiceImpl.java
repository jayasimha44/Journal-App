package com.learn.journalapp.service.impl;

import com.learn.journalapp.api.response.WeatherResponse;
import com.learn.journalapp.cache.AppCache;
import com.learn.journalapp.constants.Placeholders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl {
    @Value("${weather.api.key}")
    private String apiKey;
//    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    private final RestTemplate restTemplate;
    private final AppCache appCache;
    private final RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get(city, WeatherResponse.class);
        if (weatherResponse != null) {
            log.info("you are getting data from Redis Cache!");
            return weatherResponse;
        } else {
            String updatedAPI = appCache.APP_CACHE.get(Placeholders.WEATHER_API).replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(updatedAPI, HttpMethod.GET, null, WeatherResponse.class);
            log.info("you are getting data from API Call!");
           if (response.getBody() != null) {
               redisService.set(city, response.getBody(), 300L);
           }
            return response.getBody();
        }
    }
}
