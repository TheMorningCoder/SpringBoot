package com.example.weather_forecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationResponse {

    @JsonProperty("Key")
    private String key;

    // Other fields can be added if necessary

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
