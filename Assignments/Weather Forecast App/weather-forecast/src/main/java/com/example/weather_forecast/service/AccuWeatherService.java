package com.example.weather_forecast.service;


import com.example.weather_forecast.model.AccuWeatherDataModel;


public interface AccuWeatherService {
    AccuWeatherDataModel getWeather(String city, String zip, String countryCode);
}
