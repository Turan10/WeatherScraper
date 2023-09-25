package dat;


import dto.WeatherDTO;
import setup.Paginated;
import setup.Serialization;

public class Main {
    public static void main(String[] args) {
        Paginated weatherScraper = new Paginated();

        Serialization serialization = new Serialization();
      //  String json = serialization.serialize(weatherDTO);
      //  serialization.JsonToFile("WeatherForecast.json", json);
    }
}


