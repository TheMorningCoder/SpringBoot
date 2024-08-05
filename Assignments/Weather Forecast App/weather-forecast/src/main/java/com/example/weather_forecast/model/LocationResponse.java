package com.example.weather_forecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationResponse {
	
	// this annotation used to map JSON property names to Java field name
    @JsonProperty("Key")
    private String key;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
