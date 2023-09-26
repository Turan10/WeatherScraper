package dao;

import java.util.List;
    public interface IDAO<T, I> {
        List<T> getAll();
        T getByDate(String date);
        T getByDateAndLocation(String date, String location);
        T getYesterdayByDate(String date);

        T getByDateAndSpecificField(String date, String fieldName, Object value);

        T getOneBySpecificField(String fieldName, Object value);
    }