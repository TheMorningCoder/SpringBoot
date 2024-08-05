package com.example.weather_forecast.service;


import com.example.weather_forecast.model.OpenWeatherDataModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
// this annotation marks this class as a Spring service component... to be registered as spring bean
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final String apiKey = "69017e0b57a66b777aec1aec85cb05a0";
    private final String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?zip={zip},{countryCode}&appid={apiKey}";

    @Override
    public OpenWeatherDataModel getWeather(String zip, String countryCode) {
    	
    	
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate class in Spring is a synchronous client to perform HTTP requests
        //Here it is used to call an external API and retrieve weather data.
        
        String url = urlTemplate.replace("{zip}", zip).replace("{countryCode}", countryCode).replace("{apiKey}", apiKey);
        
        
        OpenWeatherDataModel response = restTemplate.getForObject(url, OpenWeatherDataModel.class);
        //getForObject performs HTTP GET requests to the specified URL 
        //also converts the HTTP response body into an instance of OpenWeatherDataModel
        
        
        return response;
    }
}
