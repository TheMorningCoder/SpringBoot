package com.example.weather_forecast.service;


import com.example.weather_forecast.model.OpenWeatherDataModel;


public interface OpenWeatherService {
    OpenWeatherDataModel getWeather(String zip, String countryCode);
}
