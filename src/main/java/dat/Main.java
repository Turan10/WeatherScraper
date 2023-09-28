package dat;

import dao.WeatherDao;
import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import entities.*;
import jakarta.persistence.EntityManager;
import service.WeatherService;
import utility.EntityManagerInit;
import utility.ReadJson;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        ReadJson<WeatherDTO> jsonReader2 = new ReadJson<>();
        ReadJson<ApiWeatherDTO> jsonReader = new ReadJson<>();

        List<WeatherDTO> weatherDTOs = jsonReader2.deserializeScrapedWeatherData("src/main/resources/WeatherForecast.json");
        List<ApiWeatherDTO> apiWeatherDTOs = jsonReader.deserializeApiWeatherData("src/main/resources/ApiForecast.json");

        EntityManager em = EntityManagerInit.getEntityManagerFactory().createEntityManager();
        WeatherDao weatherDao = new WeatherDao();
        try {
            em.getTransaction().begin();

            for (ApiWeatherDTO dto : apiWeatherDTOs) {
                Location location = weatherDao.findLocationByName(em, dto.getLocationName());
                if (location == null) {
                    location = new Location();
                    location.setLocationName(dto.getLocationName());
                    em.persist(location);
                    em.flush(); // Force commit the newly created location
                }

                ApiWeather apiWeather = weatherService.mapApiDtoToApiWeatherEntity(em, dto);
                em.persist(apiWeather);
            }

            String predefinedLocationName = "K\u00f8benhavn";
            Location location = weatherDao.findLocationByName(em, predefinedLocationName);

            if (location == null) {
                location = new Location();
                location.setLocationName(predefinedLocationName);
                em.persist(location);
                em.flush(); // Force commit the newly created location
            }

            for (WeatherDTO dto : weatherDTOs) {
                WeatherForecast weatherForecast = weatherService.mapDtoToWeatherForecastEntity(dto);
                weatherForecast.setLocation(location);
                em.persist(weatherForecast);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}