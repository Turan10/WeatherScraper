package dao;


import entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import utility.EntityManagerInit;

public class WeatherDao {

    public <T> void save(EntityManager em, T entity) {
        em.persist(entity);
    }


    public Location findLocationByName(EntityManager em, String locationName) {
        TypedQuery<Location> query = em.createQuery(
                "SELECT l FROM Location l WHERE l.locationName = :locationName", Location.class);
        query.setParameter("locationName", locationName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
