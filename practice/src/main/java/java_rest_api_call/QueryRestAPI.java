package java_rest_api_call;
/*
Oracle Question
REST API: Discounted Price
Given a barcode, query the API at
https://jsonmock.hackerrank.com/api/inventory?barcode=barcode and return
the item's discounted price.
The response is a JSON object with 5 fields. The essential field is data.
• data: Either an empty array or an array with a single object that contains the
item's record.
• In the data array, the item has the following schema:。 barcode-the barcode for the product (String)o price-the gross selling price (Number)
。 discount the discount percent to apply (Number).
。 Some fields that are not of interest.
page, per_page, total, total_pages, etc. are not required for this task.
If the barcode is found, the data array contains exactly 1 element. If not, it is
empty and the function should return '-1.
Example:
https://jsonmock.hackerrank.com/api/inventory?barcode=74001755 is:
"barcode"："74001755",
"item": "Ball Gown",
"category":"Full Body Outfits",
"price":785,
"discount":7,
"available":1
10
11
12
Use the "discount" and the "price" properties to calculate the discounted price
rounded to the nearest integer.
discountedPrice = price -((discount / 100) * price)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class QueryRestAPI {
    public static void main(String[] args) {
        // Example barcode value for querying the API
        int barcode = 74001755;

        // Fetch the discounted price for the given barcode
        int discountedPrice = getDiscountedPrice(barcode);

        // Print the discounted price to the console
        System.out.println("Discounted Price: " + discountedPrice);
    }

    public static int getDiscountedPrice(int barcode) {
        try {
            // Construct the API URL with the given barcode
            String apiUrl = "https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode;

            // Create a URL object
            @SuppressWarnings("deprecation")
			URL url = new URL(apiUrl);

            // Open a connection to the API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP method to GET
            connection.setRequestMethod("GET");

            // Set the request header to accept JSON responses
            connection.setRequestProperty("Accept", "application/json");

            // Create a BufferedReader to read the response stream
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Read the response line by line and build the response string
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            // Close the BufferedReader
            br.close();

            // Parse the response JSON string
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract the "data" array from the JSON response
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // If the "data" array is empty, return -1 (indicating no data found)
            if (dataArray.isEmpty()) {
                return -1;
            }

            // Get the first item in the "data" array
            JSONObject item = dataArray.getJSONObject(0);

            // Extract the price and discount values
            double price = item.getDouble("price");
            double discount = item.getDouble("discount");

            // Calculate the discounted price and return it as an integer
            return (int) Math.round(price - ((discount / 100) * price));
        } catch (Exception e) {
            // Print the stack trace if an exception occurs and return -1
            e.printStackTrace();
            return -1;
        }
    }
}
