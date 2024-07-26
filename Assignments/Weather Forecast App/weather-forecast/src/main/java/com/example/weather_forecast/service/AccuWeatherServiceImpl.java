package com.example.weather_forecast.service;

import com.example.weather_forecast.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccuWeatherServiceImpl implements AccuWeatherService {

    private final String apiKey = "V3pYNoO27o0Ny14moRQZagTC95Uzvjc6";
    private final String urlTemplate = "http://api.accuweather.com/locations/v1/cities/search?apikey={apiKey}&q={city}";

    @Override
    public WeatherResponse getWeather(String city, String zip, String countryCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = urlTemplate.replace("{apiKey}", apiKey).replace("{city}", city);
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response;
    }
}
