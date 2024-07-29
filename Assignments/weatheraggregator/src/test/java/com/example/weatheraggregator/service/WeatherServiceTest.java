package com.example.weatheraggregator.service;

import com.example.weatheraggregator.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather() {
        String city = "Noida";
        String zip = "110025";
        String countryCode="IN";
        	

        // Mock AccuWeather response
        WeatherResponse accuWeatherResponse = new WeatherResponse();
        accuWeatherResponse.setWeatherText("Clouds and sun");
        accuWeatherResponse.setTemperature(new WeatherResponse.Temperature());
        when(restTemplate.getForObject(contains("accuweather"), eq(WeatherResponse.class))).thenReturn(accuWeatherResponse);

        // Mock OpenWeather response
        WeatherResponse openWeatherResponse = new WeatherResponse();
        openWeatherResponse.setFeelsLike(new WeatherResponse.Temperature());
        when(restTemplate.getForObject(contains("openweathermap"), eq(WeatherResponse.class))).thenReturn(openWeatherResponse);

        WeatherResponse weatherResponse = weatherService.getWeather(city, zip,countryCode);

        assertNotNull(weatherResponse);
        assertEquals("Clouds and sun", weatherResponse.getWeatherText());
    }
}
