package com.example.weatheraggregator.service;

import com.example.weatheraggregator.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private final String accuWeatherApiKey = "O3pWXByyepG5BeukHHQAZ4wlZVtV32TC";
    private final String openWeatherApiKey = "69017e0b57a66b777aec1aec85cb05a0";

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherResponse getWeather(String city, String zip,String countryCode) {
        CompletableFuture<WeatherResponse> accuWeatherFuture = CompletableFuture.supplyAsync(() -> getAccuWeather(city), executorService);
        CompletableFuture<WeatherResponse> openWeatherFuture = CompletableFuture.supplyAsync(() -> getOpenWeather(zip,countryCode), executorService);

        return CompletableFuture.allOf(accuWeatherFuture, openWeatherFuture)
                .thenApply(v -> {
                    WeatherResponse accuWeather = accuWeatherFuture.join();
                    WeatherResponse openWeather = openWeatherFuture.join();
                    return aggregateWeather(accuWeather, openWeather);
                })
                .join();
    }

    private WeatherResponse getAccuWeather(String city) {
        String locationUrl = String.format("https://dataservice.accuweather.com/locations/v1/search?q=%s&apikey=%s", city, accuWeatherApiKey);
        String locationKey = restTemplate.getForObject(locationUrl, String.class);

        String weatherUrl = String.format("https://dataservice.accuweather.com/currentconditions/v1/%s?apikey=%s", locationKey, accuWeatherApiKey);
        return restTemplate.getForObject(weatherUrl, WeatherResponse.class);
    }

    public WeatherResponse getOpenWeather(String zip, String countryCode) {
    	final String apiKey = "69017e0b57a66b777aec1aec85cb05a0";
        final String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?zip={zip},{countryCode}&appid={apiKey}";

        RestTemplate restTemplate = new RestTemplate();
        String url = urlTemplate.replace("{zip}", zip).replace("{countryCode}", countryCode).replace("{apiKey}", apiKey);
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response;
    }
    

    private WeatherResponse aggregateWeather(WeatherResponse accuWeather, WeatherResponse openWeather) {
        WeatherResponse aggregated = new WeatherResponse();
        aggregated.setWeatherText(accuWeather.getWeatherText());
        aggregated.setHasPrecipitation(accuWeather.isHasPrecipitation());
        aggregated.setPrecipitationType(accuWeather.getPrecipitationType());
        aggregated.setIsDayTime(accuWeather.isDayTime());
        aggregated.setTemperature(accuWeather.getTemperature());

        aggregated.setFeelsLike(openWeather.getFeelsLike());
        aggregated.setPressure(openWeather.getPressure());
        aggregated.setHumidity(openWeather.getHumidity());
        aggregated.setVisibility(openWeather.getVisibility());
        aggregated.setWind(openWeather.getWind());
        aggregated.setSunrise(openWeather.getSunrise());
        aggregated.setSunset(openWeather.getSunset());

        return aggregated;
    }
}
