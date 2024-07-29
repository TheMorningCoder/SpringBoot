package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("DailyForecasts")
    @Expose
    public DailyForecast[] dailyForecasts;

    public DailyForecast[] getDailyForecasts() {
        return dailyForecasts;
    }
}
