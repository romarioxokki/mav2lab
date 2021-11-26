package service;

import baseClasses.StudySubject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
    public static void start() {

        new Thread(() -> {
            while (true) {
                ObjectMapper objectMapper = new ObjectMapper();
                int count = 0;
                try {
                    count = objectMapper.readValue(new File("src/main/resources/crashReport/report.json"),Integer.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (!(Controller.queue.size() == 0)) {
                    Map<String, Object> params = Controller.queue.poll();
                    String command = (String) params.get("command");
                    String type = (String) params.get("type");
                    if (command.equals("create")) {
                        if (type.equals("teacher")) {
                            try {
                                PeopleService.createTeacher((String) params.get("name"),(String) params.get("birthdate"),(String)params.get("phone"),(String) params.get("subject"),(String) params.get("workingHours"));
                            } catch (IOException e) {e.printStackTrace();}
                        } else {
                            try {
                                PeopleService.createStudent((String) params.get("name"),(String) params.get("birthdate"),(String) params.get("phone"),(StudySubject[]) params.get("baseClasses.StudySubject"),(Map) params.get("averageMarks"));
                            } catch (IOException e) {e.printStackTrace();}
                        }
                        count++;
                    } else if (command.equals("update")) {
                        try {
                            PeopleService.updatePerson((Integer) params.get("id"),(Map) params,(String) params.get("type"));
                        } catch (IOException e) {e.printStackTrace();}
                    } else {
                        PeopleService.delete((int) params.get("id"));
                    }


                }
                try {
                    objectMapper.writeValue(new File("src/main/resources/crashReport/report.json"),count);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }
}
