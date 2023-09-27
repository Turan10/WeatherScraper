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
        apiWeather.setWeatherCondition(dto.getCurrentData().getSkyText());

        return apiWeather;
    }

    public WeatherData mapApiDtoToWeatherDataEntity(EntityManager em, ApiWeatherDTO dto) {
        WeatherData weatherData = new WeatherData();

        Location location = fetchOrCreateLocation(em, dto.getLocationName());
        weatherData.setLocation(location);

        CurrentWeatherData currentData = new CurrentWeatherData();
        currentData.setSkyText(dto.getCurrentData().getSkyText());
        currentData.setHumidity(dto.getCurrentData().getHumidity());

        weatherData.setCurrentWeatherData(currentData);

        return weatherData;
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

    public void saveWeatherData(EntityManager em, WeatherData weatherData) {
        // Save associated entities first
        if(weatherData.getCurrentWeatherData() != null && weatherData.getCurrentWeatherData().getId() == null) {
            weatherDAO.save(em, weatherData.getCurrentWeatherData());
        }
        weatherDAO.save(em, weatherData);
    }

    public void saveWeatherForecast(EntityManager em, WeatherForecast forecast) {
        weatherDAO.save(em, forecast);
    }
}
