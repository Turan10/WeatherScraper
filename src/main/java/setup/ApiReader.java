package setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dto.ApiWeatherDTO;
import dto.ApiWeatherDTO;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;


public class ApiReader {


    public ApiWeatherDTO getWeatherFromApi(String city) {


        Gson gson = new GsonBuilder().setPrettyPrinting().create();



           ApiWeatherDTO weatherApiDto = null;

            OkHttpClient client = new OkHttpClient();


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

                    weatherApiDto = gson.fromJson(jsonData, ApiWeatherDTO.class);



                }
            } catch (IOException e) {
                System.out.println("Exception occurred while making the request: " + e.getMessage());
            }
            return weatherApiDto;
        }


    }



