package dat;

import dao.WeatherDao;
import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import entities.*;
import jakarta.persistence.EntityManager;
import service.WeatherService;
import setup.ApiReader;
import utility.EntityManagerInit;
import utility.ReadJson;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApiReader apiReader = new ApiReader();
        WeatherService weatherService = new WeatherService();
        ReadJson<ApiWeatherDTO> jsonReader = new ReadJson<>();
        ReadJson<WeatherDTO> jsonReader2 = new ReadJson<>();

        List<WeatherDTO> weatherDTOs = jsonReader2.deserializeScrapedWeatherData("src/main/resources/WeatherForecast.json");
        List<ApiWeatherDTO> apiWeatherDTOs = jsonReader.deserializeApiWeatherData("src/main/resources/ApiForecast.json");
        System.out.println(weatherDTOs);
        System.out.println(apiWeatherDTOs);

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
                }

                ApiWeather apiWeather = weatherService.mapApiDtoToApiWeatherEntity(em, dto);
                apiWeather.setLocation(location);

                WeatherData weatherData = weatherService.mapApiDtoToWeatherDataEntity(em, dto);
                weatherData.setLocation(location);

                CurrentWeatherData currentData = weatherData.getCurrentWeatherData();
                if (currentData != null) {
                    if (currentData.getId() == null) {
                        // Let the cascade handle this when you persist WeatherData
                        weatherData.setCurrentWeatherData(currentData);
                    } else {
                        CurrentWeatherData managedCurrentData = em.merge(currentData);
                        weatherData.setCurrentWeatherData(managedCurrentData);
                    }
                }

                em.persist(apiWeather);
                em.persist(weatherData);
            }

            for (WeatherDTO dto : weatherDTOs) {
                String predefinedLocationName = "K\u00f8benhavn";
                Location location = weatherDao.findLocationByName(em, predefinedLocationName);

                if (location == null) {
                    location = new Location();
                    location.setLocationName(predefinedLocationName);
                    em.persist(location);
                }

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
