import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PeopleDAO implements Dao<Person> {
    String fileName;

    public PeopleDAO(String fileName ) {

        this.fileName = fileName;
    }


    @Override
    public void save(Person person) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = person.getId()+".json";
        File file = new File(fileName+path);
        objectMapper.writeValue(file,person);
    }

    @Override
    public void update(Person person,  Map params) throws IOException {
       ObjectMapper objectMapper = new ObjectMapper();
       String path = person.getId()+".json";
       if (Files.exists(Path.of(path))){
           File file = new File(path);
           file.delete();
       }
        File file = new File(fileName+path);
        objectMapper.writeValue(new File(fileName + path),person);
    }

    @Override
    public void delete(int id) {
        String path = id + ".json";
        File file = new File(fileName+path);
        file.delete();

    }

    @Override
    public Person findById(int id,String perk) throws IOException {
        File file = new File (fileName+id+".json");
        ObjectMapper objectMapper = new ObjectMapper();
         if(perk.equals( "Person")){
            Person person = objectMapper.readValue(file,Person.class);
            return person;}
         if (perk.equals("Student")){
             Student student = objectMapper.readValue(file,Student.class);
             return student;
         }
         if (perk.equals("Teacher")){
             Teacher teacher = objectMapper.readValue(file,Teacher.class);
             return teacher;
         }
         return null;

    }
}
