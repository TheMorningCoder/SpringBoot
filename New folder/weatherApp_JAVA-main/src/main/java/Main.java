import Controller.ServerRequest;
import Model.Location;
import Model.Temperature;
import Model.WeatherResponse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Temperature temperature;

        System.out.println("City: ");
        String city = sc.nextLine();

        System.out.println("Country: ");
        String country = sc.nextLine();

        //not checking if city/country is ok
        //assuming user is a kind soul

        Location location = ServerRequest.createRequestFindCity(city, country);
        WeatherResponse weatherResponse = ServerRequest.createRequestForecastAPIOneDay(location.getLocationId());

        temperature = weatherResponse.getDailyForecasts()[0].getTemperature();

        System.out.println(weatherResponse.getDailyForecasts()[0].getDate());
        System.out.println("Max temp: " + temperature.getMaximum().getValue() + temperature.getMaximum().getUnit());
        System.out.println("Max temp: " + temperature.getMinimum().getValue() + temperature.getMinimum().getUnit());
    }
}
