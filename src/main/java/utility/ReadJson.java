package utility;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import dto.WeatherForecastContainerDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadJson<T>{

    private final Gson gson = new Gson();

    public List<T> readDataFromJson(String filename, Class<T> dtoClass) {
        List<T> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T data = gson.fromJson(line, dtoClass);
                dataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }



    public T readEntireJsonFile(String filename, Class<T> dtoClass) {
        T data = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            data = gson.fromJson(reader, dtoClass);
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return data;
    }


    public List<WeatherDTO> deserializeScrapedWeatherData(String jsonPath) {
        try {
            Reader reader = new FileReader(jsonPath);
            WeatherForecastContainerDTO weatherList = gson.fromJson(reader, WeatherForecastContainerDTO.class);
            return weatherList.getWeathers();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<ApiWeatherDTO> deserializeApiWeatherData(String jsonPath) {
        List<ApiWeatherDTO> apiWeatherDTOs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(jsonPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ApiWeatherDTO apiWeather = gson.fromJson(line, ApiWeatherDTO.class);
                apiWeatherDTOs.add(apiWeather);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return apiWeatherDTOs;
    }

}
