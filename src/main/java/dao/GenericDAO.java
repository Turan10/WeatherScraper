package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenericDAO<T, I> implements IDAO<T, I> {

    private final Class<T> type;
    private final EntityManager entityManager;

    public GenericDAO(Class<T> type, EntityManager em) {
        this.type = type;
        this.entityManager = Persistence.createEntityManagerFactory("your_persistence_unit_name").createEntityManager();
    }

    @Override
    public List<T> getAll() {
        String queryStr = "SELECT e FROM " + type.getSimpleName() + " e";
        return entityManager.createQuery(queryStr, type).getResultList();
    }

    @Override
    public T getByDate(String date) {
        String queryStr = "SELECT e FROM " + type.getSimpleName() + " e WHERE e.date = :date";
        TypedQuery<T> query = entityManager.createQuery(queryStr, type).setParameter("date", date);
        return query.getSingleResult();
    }

    @Override
    public T getByDateAndLocation(String date, String location) {
            try {
                String queryStr = "SELECT e FROM " + type.getSimpleName() + " e WHERE e.date = :date AND e.location = :location";
                TypedQuery<T> query = entityManager.createQuery(queryStr, type)
                        .setParameter("date", date)
                        .setParameter("location", location);
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }


    @Override
    public T getYesterdayByDate(String date) {
        try {
            // Parse the string to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern to match your date format
            LocalDate localDate = LocalDate.parse(date, formatter);

            // Subtract one day to get yesterday’s date
            LocalDate yesterdayDate = localDate.minusDays(1);

            // Convert it back to string
            String yesterdayDateString = yesterdayDate.format(formatter);

            String queryStr = "SELECT e FROM " + type.getSimpleName() + " e WHERE e.date = :date";
            TypedQuery<T> query = entityManager.createQuery(queryStr, type)
                    .setParameter("date", yesterdayDateString);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // or handle it as appropriate for your use case
        }
    }


    @Override
    public T getByDateAndSpecificField(String date, String fieldName, Object value) {
        String queryStr = "SELECT e FROM " + type.getSimpleName() + " e WHERE e.date = :date AND e." + fieldName + " = :value";
        TypedQuery<T> query = entityManager.createQuery(queryStr, type)
                .setParameter("date", date)
                .setParameter("value", value);
        return query.getSingleResult();
    }

    @Override
    public T getOneBySpecificField(String fieldName, Object value) {
        String queryStr = "SELECT e FROM " + type.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        TypedQuery<T> query = entityManager.createQuery(queryStr, type).setParameter("value", value);
        return query.getSingleResult();
    }

    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
