package com.example.weather_forecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccuWeatherDataModel {

    @JsonProperty("LocalObservationDateTime")
    private String localObservationDateTime;

    @JsonProperty("EpochTime")
    private long epochTime;

    @JsonProperty("WeatherText")
    private String weatherText;

    @JsonProperty("WeatherIcon")
    private int weatherIcon;

    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;

    @JsonProperty("PrecipitationType")
    private String precipitationType;

    @JsonProperty("IsDayTime")
    private boolean isDayTime;

    @JsonProperty("Temperature")
    private Temperature temperature;

    @JsonProperty("MobileLink")
    private String mobileLink;

    @JsonProperty("Link")
    private String link;

    // Getters and Setters

    public String getLocalObservationDateTime() {
        return localObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        this.localObservationDateTime = localObservationDateTime;
    }

    public long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(long epochTime) {
        this.epochTime = epochTime;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public boolean isHasPrecipitation() {
        return hasPrecipitation;
    }

    public void setHasPrecipitation(boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

    public void setPrecipitationType(String precipitationType) {
        this.precipitationType = precipitationType;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    public void setIsDayTime(boolean isDayTime) {
        this.isDayTime = isDayTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

   }
