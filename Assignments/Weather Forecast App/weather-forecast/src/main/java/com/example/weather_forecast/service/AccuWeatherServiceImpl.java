package com.example.weather_forecast.service;

import com.example.weather_forecast.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AccuWeatherServiceImpl implements AccuWeatherService {

    private static final Logger logger = LoggerFactory.getLogger(AccuWeatherServiceImpl.class);
    private final String apiKey = "V3pYNoO27o0Ny14moRQZagTC95Uzvjc6";
    private final String urlTemplate = "https://dataservice.accuweather.com/locations/v1/cities/search";

    @Override
    public WeatherResponse getWeather(String city, String zip, String countryCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(urlTemplate)
                .queryParam("apikey", apiKey)
                .queryParam("q", city)
                .toUriString();

        logger.info("************************************************************************Request URL: " + url+"********************************************************");

        try {
            // Expecting an array of results
            WeatherResponse[] responses = restTemplate.getForObject(url, WeatherResponse[].class);
            if (responses != null && responses.length > 0) {
                // Return the first result's Key
                WeatherResponse response = responses[0];
                return response;
            } else {
                // Handle case where no results are found
                logger.error("No results found for city: " + city);
                return null;
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while fetching weather data: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            logger.error("Response headers: " + e.getResponseHeaders());
            logger.error("Request URL: " + url);
            throw e;
        }
    }
}
