package Controller;

import Model.DailyForecast;
import Model.Location;
import Model.ENV;
import Model.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class ServerRequest {

    public static WeatherResponse createRequestForecastAPIOneDay(String locationId){
        OkHttpClient client = new OkHttpClient();
        WeatherResponse weatherResponse = null;
        Request request;
        Response response;
        String jsonData;
        Gson gson;

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ENV.OneDayForecast_URL)).newBuilder();

        urlBuilder.addPathSegment(locationId);
        urlBuilder.addQueryParameter("apikey", ENV.API_KEY);
        urlBuilder.addQueryParameter("metric", "true");

        String url = urlBuilder.build().toString();

        request = new Request.Builder().url(url).build();

        try{
            response = client.newCall(request).execute();

            gson = new GsonBuilder().setPrettyPrinting().create();
            jsonData = response.body().string();
            weatherResponse = gson.fromJson(jsonData, WeatherResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherResponse;
    }

    public static Location createRequestFindCity(String city, String country){
        OkHttpClient client = new OkHttpClient();
        Request request;
        Response response;
        Location[] location = null;
        String jsonData;
        Gson gson;

        HttpUrl urlBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", ENV.API_KEY)
                .addQueryParameter("q", city + "," + country)
                .addQueryParameter("language", "es")
                .build();

        request = new Request.Builder().url(urlBuilder).build();

        try {
            response = client.newCall(request).execute();

            gson = new GsonBuilder().setPrettyPrinting().create();
            jsonData = response.body().string();
            location = gson.fromJson(jsonData, Location[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return location[0];
    }
}
