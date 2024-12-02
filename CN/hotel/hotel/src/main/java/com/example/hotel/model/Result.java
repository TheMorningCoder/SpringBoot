package com.example.hotel.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Result {
    public static void main(String[] args) {
        int barcode = 74001755; // Example barcode
        int discountedPrice = getDiscountedPrice(barcode);
        System.out.println("Discounted Price: " + discountedPrice);
    }

    public static int getDiscountedPrice(int barcode) {
        try {
            String apiUrl = "https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray dataArray = jsonResponse.getJSONArray("data");
            if (dataArray.isEmpty()) {
                return -1;
            }

            JSONObject item = dataArray.getJSONObject(0);
            double price = item.getDouble("price");
            double discount = item.getDouble("discount");

            return (int) Math.round(price - ((discount / 100) * price));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
