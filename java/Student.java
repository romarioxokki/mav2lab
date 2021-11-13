import java.util.Map;

public class Student extends Person {
    private StudySubject[] studySubject;
    private Map averageMark;

    public Student(String name, String birthdate, String phone, StudySubject[] studySubject, Map averageMark) {
        super(name, birthdate, phone);
        this.studySubject = studySubject;
        this.averageMark = averageMark;

    }

    public void setAverageMark(Map averageMark) {
        this.averageMark = averageMark;
    }

    public Map getAverageMark() {
        return averageMark;
    }

    public void setStudySubject(StudySubject[] studySubject) {
        this.studySubject = studySubject;
    }

    public StudySubject[] getStudySubject() {
        return studySubject;
    }
}
