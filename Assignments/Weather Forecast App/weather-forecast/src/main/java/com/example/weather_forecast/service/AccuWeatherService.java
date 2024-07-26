package com.example.weather_forecast.service;


import com.example.weather_forecast.model.WeatherResponse;

public interface AccuWeatherService {
    WeatherResponse getWeather(String city, String zip, String countryCode);
}
