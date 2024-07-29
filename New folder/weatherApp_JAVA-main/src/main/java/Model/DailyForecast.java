package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DailyForecast {
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Temperature")
    @Expose
    private Temperature temperature;

    public DailyForecast(String date, Temperature temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
