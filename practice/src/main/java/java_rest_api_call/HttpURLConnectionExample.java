package java_rest_api_call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

	 public static void main(String args[]) throws IOException{
		 
		try {
				 //Make connection
				 URL url= new URL("https://jsonmock.hackerrank.com/api/inventory?barcode=74001755");
				 HttpURLConnection connection= (HttpURLConnection) url.openConnection();
				 
				 //Configure Connection
				 connection.setRequestMethod("GET");
				 connection.setRequestProperty("Content-type", "application/json");
				 connection.setConnectTimeout(5000);
				 connection.setReadTimeout(5000);
				 
				 //Read Response Code
				 int status=connection.getResponseCode();
				 System.out.println("Response Code: "+status);
				 
				 //Read Response
				 BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				 StringBuilder response=new StringBuilder();
				 String line;
				  while((line=br.readLine())!=null)
					  response.append(line);
				  br.close();
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		 
	}
}
