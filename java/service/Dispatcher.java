package service;

import baseClasses.StudySubject;
import service.Controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
    public static void start() {
        new Thread(() -> {
            while (true) {
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
                    } else if (command.equals("update")) {
                        try {
                            PeopleService.updatePerson((Integer) params.get("id"),(Map) params,(String) params.get("type"));
                        } catch (IOException e) {e.printStackTrace();}
                    } else {
                        PeopleService.delete((int) params.get("id"));
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }
}
