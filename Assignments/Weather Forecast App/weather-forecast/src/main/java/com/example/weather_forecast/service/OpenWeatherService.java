package com.example.weather_forecast.service;


import com.example.weather_forecast.model.WeatherResponse;

public interface OpenWeatherService {
    WeatherResponse getWeather(String zip, String countryCode);
}
