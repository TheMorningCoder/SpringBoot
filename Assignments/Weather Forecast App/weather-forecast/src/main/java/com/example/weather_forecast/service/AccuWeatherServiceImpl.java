package com.example.weather_forecast.service;

import com.example.weather_forecast.model.AccuWeatherDataModel;
import com.example.weather_forecast.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AccuWeatherServiceImpl implements AccuWeatherService {

    private static final Logger logger = LoggerFactory.getLogger(AccuWeatherServiceImpl.class);
    private final String apiKey = "CHzh1GeQUpLUcCpV60QM0woO4aOmrnrj";
    private final String locationUrlTemplate = "https://dataservice.accuweather.com/locations/v1/cities/search";
    private final String weatherUrlTemplate = "https://dataservice.accuweather.com/currentconditions/v1/";

    @Override
    public AccuWeatherDataModel getWeather(String city, String zip, String countryCode) {
        RestTemplate restTemplate = new RestTemplate();
        String locationUrl = UriComponentsBuilder.fromHttpUrl(locationUrlTemplate)
                .queryParam("apikey", apiKey)
                .queryParam("q", city)
                .toUriString(); 
        
        try {
            // Fetch location key
            LocationResponse[] locationResponses = restTemplate.getForObject(locationUrl, LocationResponse[].class);
            if (locationResponses != null && locationResponses.length > 0) {
                LocationResponse locationResponse = locationResponses[0];
                String locationKey = locationResponse.getKey();
                
                // Fetch weather data using the location key
                AccuWeatherDataModel weatherResponse = getWeatherByLocationKey(locationKey);
                return weatherResponse;
            } else {
                logger.error("No results found for city: " + city);
                return null;
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while fetching location data: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw e;
        }
    }

    private AccuWeatherDataModel getWeatherByLocationKey(String locationKey) {
        RestTemplate restTemplate = new RestTemplate();
        String weatherUrl = UriComponentsBuilder.fromHttpUrl(weatherUrlTemplate + locationKey)
                .queryParam("apikey", apiKey)
                .toUriString();
       
        logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^               Request URL for weather: " + weatherUrl+"                          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
       

        try {
            AccuWeatherDataModel[] weatherResponses = restTemplate.getForObject(weatherUrl, AccuWeatherDataModel[].class);
            if (weatherResponses != null && weatherResponses.length > 0) {
                return weatherResponses[0];
            } else {
                logger.error("No weather data found for location key: " + locationKey);
                return null;
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while fetching weather data: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw e;
        }
    }
}





//package com.example.weather_forecast.service;
//
//import com.example.weather_forecast.model.WeatherResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Service
//public class AccuWeatherServiceImpl implements AccuWeatherService {
//
//    private static final Logger logger = LoggerFactory.getLogger(AccuWeatherServiceImpl.class);
//    private final String apiKey = "Diaydrk5OA5ts1puv4Oetvc3RXzv72uE";
//    private final String locationUrlTemplate = "https://dataservice.accuweather.com/locations/v1/cities/search";
//    private final String weatherUrlTemplate = "https://dataservice.accuweather.com/currentconditions/v1/";
//
//    @Override
//    public WeatherResponse getWeather(String city, String zip, String countryCode) {
//        RestTemplate restTemplate = new RestTemplate();
//        String locationUrl = UriComponentsBuilder.fromHttpUrl(locationUrlTemplate)
//                .queryParam("apikey", apiKey)
//                .queryParam("q", city)
//                .toUriString();
//        logger.info("*************************************************************************************************");
//        logger.info("Request URL for location: " + locationUrl);
//        logger.info("*************************************************************************************************");
//
//        try {
//            // Fetch location key
//            WeatherResponse[] locationResponses = restTemplate.getForObject(locationUrl, WeatherResponse[].class);
//            if (locationResponses != null && locationResponses.length > 0) {
//                WeatherResponse locationResponse = locationResponses[0];
//                String locationKey = locationResponse.getKey();
//
//                // Fetch weather data using the location key
//                WeatherResponse weatherResponse = getWeatherByLocationKey(locationKey);
//                return weatherResponse;
//            } else {
//                logger.error("No results found for city: " + city);
//                return null;
//            }
//        } catch (HttpClientErrorException e) {
//            logger.error("HTTP error while fetching location data: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
//            throw e;
//        }
//    }
//
//    private WeatherResponse getWeatherByLocationKey(String locationKey) {
//        RestTemplate restTemplate = new RestTemplate();
//        String weatherUrl = UriComponentsBuilder.fromHttpUrl(weatherUrlTemplate + locationKey)
//                .queryParam("apikey", apiKey)
//                .toUriString();
//        logger.info("*************************************************************************************************");
//        logger.info("Request URL for weather: " + weatherUrl);
//        logger.info("*************************************************************************************************");
//
//        try {
//            WeatherResponse[] weatherResponses = restTemplate.getForObject(weatherUrl, WeatherResponse[].class);
//            if (weatherResponses != null && weatherResponses.length > 0) {
//                return weatherResponses[0];
//            } else {
//                logger.error("No weather data found for location key: " + locationKey);
//                return null;
//            }
//        } catch (HttpClientErrorException e) {
//            logger.error("HTTP error while fetching weather data: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
//            throw e;
//        }
//    }
//}
