package java_rest_api_call;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
public class PracticeHttpURLConnection {
	public static void main(String args[]) throws IOException {
		try {
			//Make Connection
			@SuppressWarnings("deprecation")
			URL url =new URL("https://jsonmock.hackerrank.com/api/inventory?barcode=74001755");
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			
			//Configure connection
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-type", "application/json");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			
			//Get response code
			int status=connection.getResponseCode();
			System.out.println("Response status code: "+status);
			
			//Read response
			if(status==200) {
				BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response=new StringBuilder();
				String line;
				while((line=br.readLine())!=null)
					response.append(line);
				br.close();			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
