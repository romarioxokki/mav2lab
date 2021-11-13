

public class Person {
    private String name;
    private String birthdate;
    private String phone;
    private static int count = 0;
    public int id = 0;

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

