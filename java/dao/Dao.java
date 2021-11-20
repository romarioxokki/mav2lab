package dao;

import baseClasses.Person;

import java.io.IOException;
import java.util.Map;

public interface Dao<T> {

    void save(Person t) throws IOException;

    void update(Person t, Map params);

    void delete(int id);

    Person findById(int id,String perk);
}