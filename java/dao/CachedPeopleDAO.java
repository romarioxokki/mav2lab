package dao;

import baseClasses.Person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CachedPeopleDAO implements Dao {
    private Map<Integer, Person> cachedPeople = new HashMap<>();


    @Override
    public void save(Person person) throws IOException {
        cachedPeople.put(person.getId(), person);
    }

    @Override
    public void update(Person person, Map params)  {
        cachedPeople.remove(person.getId());
        cachedPeople.put(person.getId(), person);

    }

    @Override
    public void delete(int id) {
        cachedPeople.remove(id);
    }

    @Override
    public Person findById(int id, String perk)  {
        return (Person) cachedPeople.get(id);
    }
}