import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class PeopleService {
    private final static PeopleDAO serviceDao = new PeopleDAO("src/main/resources/");

    public static void createTeacher(String name, String birthdate, String phone, String lesson, String workingHours) throws IOException {
        Teacher teacher = new Teacher(name,birthdate,phone,lesson,workingHours);
        serviceDao.save(teacher);

    }
    public static void createStudent(String name, String birthdate, String phone, StudySubject[] studySubject, Map averageMark) throws IOException {
        Student student = new Student(name,birthdate,phone,studySubject,averageMark);
        serviceDao.save(student);
    }



    public Person getPerson(int id,String perk) throws IOException {
        Person  person = serviceDao.findById(id,perk);
        return person;
    }
    public static void updatePerson(int id, Map params,String perk) throws IOException {
        Person person = new Person((String) params.get("name"),(String)params.get("birthdate"),(String)params.get("phone"));
        serviceDao.delete(id);
        person.setId(id);
        serviceDao.save(person);
    }

    public static void delete(int id) {
        serviceDao.delete(id);
    }



}
