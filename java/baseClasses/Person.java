package baseClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Person {
    private String name;
    private String birthdate;
    private String phone;
    ObjectMapper objectMapper = new ObjectMapper();
    private  int count;

    {
        try {
            count = objectMapper.readValue(new File("src/main/resources/crashReport/report.json"),Integer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int id;

    public Person(String name, String birthdate, String phone) {
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.id = ++count;
    }




    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

