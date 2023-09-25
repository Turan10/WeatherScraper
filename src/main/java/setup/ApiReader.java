package setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dto.WeatherApiDto;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;


public class ApiReader {
    public static void main(String[] args) {


        Gson gson = new GsonBuilder().setPrettyPrinting().create();




            WeatherApiDto weatherApiDto = null;

            OkHttpClient client = new OkHttpClient();
            String city = "København";  // This would actually be some object if you're using a DTO

            String url = "http://vejr.eu/api.php?location=" + city + "&degree=C";

            Request request = new Request.Builder()
                    .url(url)
                      .get()
                    .addHeader("accept", "application/json")
                    .build();



            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.out.println("Failed to fetch data: " + response);
                } else {
                    String jsonData = response.body().string();
                    Serialization serialization = new Serialization();
                    serialization.JsonToFile(jsonData, "src/main/resources/ApiForecast.json");


                    System.out.println(jsonData);
                    // Parse jsonData as needed (e.g., convert to JSON object, etc.)
                }
            } catch (IOException e) {
                System.out.println("Exception occurred while making the request: " + e.getMessage());
            }
        }
    }



