package dat;


import Entities.WeatherEntity;
import dao.GenericDAO;
import dao.IDAO;
import dto.WeatherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("your_persistence_unit_name");
        EntityManager em = emf.createEntityManager();

        // Create an instance of your DAO, replace GenericDAO<YourEntityClass, Long> with actual class and ID types
        IDAO<WeatherEntity, Long> dao = new GenericDAO<>(WeatherEntity.class, em);

        try {
            // Replace with an actual date string in your format
            String dateString = "2023-09-25";

            // Call the method and print the result
            WeatherEntity result = dao.getByDate(dateString);
            System.out.println(result);
        } catch (NoResultException e) {
            System.err.println("No entity found for the provided date!");
        } finally {
            // Clean up the resources
            em.close();
            emf.close();
        }

    }
}


