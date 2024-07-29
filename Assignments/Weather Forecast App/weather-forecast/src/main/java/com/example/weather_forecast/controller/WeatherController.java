package com.example.weather_forecast.controller;

import com.example.weather_forecast.model.AccuWeatherDataModel;
import com.example.weather_forecast.model.AgrregateWeatherResponse;
import com.example.weather_forecast.model.OpenWeatherDataModel;
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
    public AgrregateWeatherResponse getWeather(@RequestParam String city, @RequestParam String zip, @RequestParam String countryCode) {
        CompletableFuture<AccuWeatherDataModel> accuWeatherFuture = CompletableFuture.supplyAsync(() -> accuWeatherService.getWeather(city, zip, countryCode));
        CompletableFuture<OpenWeatherDataModel> openWeatherFuture = CompletableFuture.supplyAsync(() -> openWeatherService.getWeather(zip, countryCode));

        CompletableFuture.allOf(accuWeatherFuture, openWeatherFuture).join();

        AccuWeatherDataModel accuWeather = accuWeatherFuture.join();
        OpenWeatherDataModel openWeather = openWeatherFuture.join();

        // Aggregate responses
        AgrregateWeatherResponse aggregatedResponse = new AgrregateWeatherResponse();
        aggregatedResponse.setWeatherText(accuWeather.getWeatherText());
        aggregatedResponse.setHasPrecipitation(accuWeather.isHasPrecipitation());
        aggregatedResponse.setPrecipitationType(accuWeather.getPrecipitationType());
        aggregatedResponse.setIsDayTime(accuWeather.isDayTime());
        aggregatedResponse.setTemperature(accuWeather.getTemperature());
        aggregatedResponse.setFeelsLike(openWeather.getMain().getFeelsLike());
        aggregatedResponse.setPressure(openWeather.getMain().getPressure());
        aggregatedResponse.setHumidity(openWeather.getMain().getHumidity());
        aggregatedResponse.setVisibility(openWeather.getVisibility());
        aggregatedResponse.setWind(openWeather.getWind());
        aggregatedResponse.setSunrise(openWeather.getSys().getSunrise());
        aggregatedResponse.setSunset(openWeather.getSys().getSunset());

        return aggregatedResponse;
    }
}
