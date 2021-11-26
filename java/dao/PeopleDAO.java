package dao;

import baseClasses.Person;
import baseClasses.Student;
import baseClasses.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PeopleDAO implements Dao<Person> {
    private String fileName;
    private ObjectMapper objectMapper;

    public PeopleDAO(String fileName) {
        this.fileName = fileName;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void save(Person person) throws IOException {
        String path = person.getId() + ".json";
        File file = new File(fileName + path);
        objectMapper.writeValue(file, person);
    }

    @Override
    public void update(Person person, Map params) {
        String path = person.getId() + ".json";
        if (Files.exists(Path.of(path))) {
            File file = new File(path);
            file.delete();
        }
        try {
            objectMapper.writeValue(new File(fileName + path), person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String path = id + ".json";
        File file = new File(fileName + path);
        file.delete();

    }

    @Override
    public Person findById(int id, String perk) {
        File file = new File(fileName + id + ".json");
        if (perk.equals("Person")) {
            Person person = reading(file, Person.class);
            return person;
        }
        if (perk.equals("Student")) {
            Student student = reading(file, Student.class);
            return student;
        }
        if (perk.equals("Teacher")) {
            Teacher teacher = reading(file, Teacher.class);
            return teacher;
        }
        return null;

    }

    private <T extends Person> T reading(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
