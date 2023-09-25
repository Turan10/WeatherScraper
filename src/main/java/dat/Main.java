package dat;


import dto.WeatherDTO;
import setup.Serialization;
import setup.WeatherScraper;

public class Main {
    public static void main(String[] args) {
        WeatherScraper weatherScraper = new WeatherScraper();

        WeatherDTO weatherDTO = weatherScraper.scrapeWeather("copenhagen");
        Serialization serialization = new Serialization();
        String json = serialization.serialize(weatherDTO);
        serialization.JsonToFile(json, "src/main/resources/WeatherForecast.json");


    }
}


