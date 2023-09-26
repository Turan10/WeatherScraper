package dao;

import Entities.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*public class WeatherDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("your_persistence_unit_name");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void createWeather(WeatherEntity weather) {
        entityManager.getTransaction().begin();
        entityManager.persist(weather);
        entityManager.getTransaction().commit();
    }

    public WeatherEntity readWeather(Long id) {
        return entityManager.find(WeatherEntity.class, id);
    }

    public List<WeatherEntity> readAllWeathers() {
        return entityManager.createQuery("SELECT w FROM WeatherEntity w", WeatherEntity.class).getResultList();
    }

    public void updateWeather(WeatherEntity weather) {
        entityManager.getTransaction().begin();
        entityManager.merge(weather);
        entityManager.getTransaction().commit();
    }

    public void deleteWeather(Long id) {
        WeatherEntity weather = readWeather(id);
        if (weather != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(weather);
            entityManager.getTransaction().commit();
        }
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}*/
