package com.example.weather_forecast.model;

import com.example.weather_forecast.model.OpenWeatherDataModel.Wind;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AgrregateWeatherResponse {
	
	// this annotation used to map JSON property names to Java field name
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
    private double feelsLike;
    private int pressure;
    private int humidity;
    private int visibility;
    private Wind wind;
    private long sunset;
    private long sunrise;

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

	public void setFeelsLike(double feelsLike) {
		this.feelsLike=feelsLike;
	}
	
	public double getFeelsLike() {
		return feelsLike;
	}

	public void setPressure(int pressure) {
		this.pressure=pressure;
		
	}
	public int getPressure() {
		return pressure;
	}
	

	public void setHumidity(int humidity) {
	this.humidity=humidity;
		
	}

	public void setVisibility(int visibility) {
		this.visibility=visibility;
		
	}

	public void setWind(Wind wind) {
		this.wind=wind;
		
	}

	public void setSunset(long sunset) {
		this.sunset=sunset;
		
	}

	public void setSunrise(long sunrise) {
		this.sunrise=sunrise;
		
	}

   
}
