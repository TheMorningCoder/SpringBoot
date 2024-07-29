package com.example.weatheraggregator.controller;

import com.example.weatheraggregator.model.WeatherResponse;
import com.example.weatheraggregator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String city, @RequestParam String zip,@RequestParam String countryCode) {
        return weatherService.getWeather(city, zip,countryCode);
    }
}
