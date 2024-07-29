package com.example.weatheraggregator.model;

public class WeatherResponse {

    private String weatherText;
    private boolean hasPrecipitation;
    private String precipitationType;
    private boolean isDayTime;
    private Temperature temperature;
    private Temperature feelsLike;
    private int pressure;
    private int humidity;
    private int visibility;
    private Wind wind;
    private long sunrise;
    private long sunset;

    // Getters and Setters

    public static class Temperature {
        private double value;
        private String unit;
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}

        // Getters and Setters
    }

    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public int getDeg() {
			return deg;
		}
		public void setDeg(int deg) {
			this.deg = deg;
		}
		public double getGust() {
			return gust;
		}
		public void setGust(double gust) {
			this.gust = gust;
		}

        // Getters and Setters
    }

	public String getWeatherText() {
		return weatherText;
	}

	public void setWeatherText(String weatherText) {
		this.weatherText = weatherText;
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

	public Temperature getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(Temperature feelsLike) {
		this.feelsLike = feelsLike;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public long getSunrise() {
		return sunrise;
	}

	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public long getSunset() {
		return sunset;
	}

	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
}
