package com.example.weather_forecast.controller;

import com.example.weather_forecast.model.WeatherResponse;
import com.example.weather_forecast.service.AccuWeatherService;
import com.example.weather_forecast.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class WeatherController {

    @Autowired
    private AccuWeatherService accuWeatherService;

    @Autowired
    private OpenWeatherService openWeatherService;

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String city, @RequestParam String zip, @RequestParam String countryCode) {
        CompletableFuture<WeatherResponse> accuWeatherFuture = CompletableFuture.supplyAsync(() -> accuWeatherService.getWeather(city, zip, countryCode));
        CompletableFuture<WeatherResponse> openWeatherFuture = CompletableFuture.supplyAsync(() -> openWeatherService.getWeather(zip, countryCode));

        CompletableFuture.allOf(accuWeatherFuture, openWeatherFuture).join();

        WeatherResponse accuWeather = accuWeatherFuture.join();
        WeatherResponse openWeather = openWeatherFuture.join();

        // Aggregate responses
        WeatherResponse aggregatedResponse = new WeatherResponse();
        aggregatedResponse.setWeatherText(accuWeather.getWeatherText());
        aggregatedResponse.setHasPrecipitation(accuWeather.isHasPrecipitation());
        aggregatedResponse.setPrecipitationType(accuWeather.getPrecipitationType());
        aggregatedResponse.setIsDayTime(accuWeather.isDayTime());
        aggregatedResponse.setTemperature(accuWeather.getTemperature());
        aggregatedResponse.setFeelsLike(openWeather.getFeelsLike());
        aggregatedResponse.setPressure(openWeather.getPressure());
        aggregatedResponse.setHumidity(openWeather.getHumidity());
        aggregatedResponse.setVisibility(openWeather.getVisibility());
        aggregatedResponse.setWind(openWeather.getWind());
        aggregatedResponse.setSunrise(openWeather.getSunrise());
        aggregatedResponse.setSunset(openWeather.getSunset());

        return aggregatedResponse;
    }
}
