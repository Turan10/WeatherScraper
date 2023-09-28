package service;

import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import entities.*;
import dao.WeatherDao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class WeatherService {

    private WeatherDao weatherDAO = new WeatherDao();

    public ApiWeather mapApiDtoToApiWeatherEntity(EntityManager em, ApiWeatherDTO dto) {
        ApiWeather apiWeather = new ApiWeather();

        Location location = fetchOrCreateLocation(em, dto.getLocationName());
        apiWeather.setLocation(location);
        apiWeather.setForecastDate(LocalDate.now().toString());

        CurrentWeatherData currentData = new CurrentWeatherData();
        currentData.setSkyText(dto.getCurrentData().getSkyText());
        currentData.setHumidity(dto.getCurrentData().getHumidity());

        apiWeather.setCurrentWeatherData(currentData);

        return apiWeather;
    }

    public WeatherForecast mapDtoToWeatherForecastEntity(WeatherDTO dto) {
        WeatherForecast forecast = new WeatherForecast();

        forecast.setDate(dto.getDate());
        forecast.setTemperature(dto.getTemp());
        forecast.setChanceOfRain(dto.getChanceOfRain());

        return forecast;
    }

    private Location fetchOrCreateLocation(EntityManager em, String locationName) {
        Location location = weatherDAO.findLocationByName(em, locationName);
        if (location == null) {
            location = new Location();
            location.setLocationName(locationName);
            weatherDAO.save(em, location);
        }
        return location;
    }

    public void saveApiWeather(EntityManager em, ApiWeather apiWeather) {
        weatherDAO.save(em, apiWeather);
    }

    public void saveWeatherForecast(EntityManager em, WeatherForecast forecast) {
        weatherDAO.save(em, forecast);
    }
}