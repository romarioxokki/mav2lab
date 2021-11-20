package baseClasses;

import baseClasses.Person;

public class Teacher extends Person {
    private String lesson;
    private String workingHours;

    public Teacher(String name, String birthdate, String phone, String lesson, String workingHours) {
        super(name, birthdate, phone);
        this.lesson = lesson;
        this.workingHours = workingHours;


    }
    private Teacher(){
        super(null, null, null);
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getLesson() {
        return lesson;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkingHours() {
        return workingHours;
    }
}
