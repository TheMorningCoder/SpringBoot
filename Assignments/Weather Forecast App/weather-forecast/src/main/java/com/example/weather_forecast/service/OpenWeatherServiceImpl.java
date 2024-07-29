package com.example.weather_forecast.service;


import com.example.weather_forecast.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final String apiKey = "69017e0b57a66b777aec1aec85cb05a0";
    private final String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?zip={zip},{countryCode}&appid={apiKey}";

    @Override
    public WeatherResponse getWeather(String zip, String countryCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = urlTemplate.replace("{zip}", zip).replace("{countryCode}", countryCode).replace("{apiKey}", apiKey);
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response;
    }
}