import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Teacher p1 = new Teacher("Vasya",
                "18.03.1232",
                "89992286851",
                "MATH",
                "10:00-12:00");
        objectMapper.writeValue(new File("target/p1.json"), p1);
        String teacherAsString = objectMapper.writeValueAsString(p1);
        Map averageMark = new HashMap();
        averageMark.put("MATH", 5.0);
        averageMark.put("RUSSIAN", 4.5);
        Student p2 = new Student("Kolya",
                "12.12.1212",
                "9323213123",
                new StudySubject[]{StudySubject.MATH, StudySubject.BIOLOGY},
                averageMark);
        String studentAsString = objectMapper.writeValueAsString(p2);
        objectMapper.writeValue(new File("target/p2.json"), p2);
        Teacher p3 = objectMapper.readValue(new File("src/test/json_teacher.json"), Teacher.class);
        String teacherFromFile = objectMapper.writeValueAsString(p3);
        System.out.println(teacherAsString);
        System.out.println(studentAsString);
        System.out.println(teacherFromFile);
        //PeopleService.createTeacher("Vasya","18.03.1232" ,"89992286851","MATH","10:00-12:00");

        Controller.start();
        Dispatcher.start();




    }

}
