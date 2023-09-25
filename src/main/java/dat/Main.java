package dat;


import dto.WeatherDTO;
import setup.Paginated;
import setup.Serialization;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Paginated weatherScraper = new Paginated();
        List<WeatherDTO> weatherDTOList = weatherScraper.scrapeWeather();
        System.out.println(weatherDTOList);

        Serialization serialization = new Serialization();
        /*for (WeatherDTO weatherDTO: weatherDTOList){
            String json = serialization.serialize(weatherDTO);
            //serialization.JsonToFile(json, "src/main/resources/WeatherForecast.json");
        }*/

       // serialization.listToJsonFile(weatherDTOList, "src/main/resources/WeatherForecast.json");

    }
}


