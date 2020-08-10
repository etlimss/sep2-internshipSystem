package shared.domain;

import java.util.ArrayList;

public class Student {

    private Long id;

    private String email;
    private String password;
    private String fName;
    private int age;
    private char gender;
    private String education;
    private String workingEx;
    private String contInfo;
    private ArrayList<Vacancy> vacanciesApplied;

    public Student(String email, String password, String fName, int age, char gender, String education, String workingEx, String contInfo) {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.age = age;
        this.gender = gender;
        this.education = education;
        this.workingEx = workingEx;
        this.contInfo = contInfo;
        vacanciesApplied = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkingEx() {
        return workingEx;
    }

    public void setWorkingEx(String workingEx) {
        this.workingEx = workingEx;
    }

    public String getContInfo() {
        return contInfo;
    }

    public void setContInfo(String contInfo) {
        this.contInfo = contInfo;
    }

    public ArrayList<Vacancy> getVacanciesApplied() {
        return vacanciesApplied;
    }

    public void addVacancy(Vacancy vacancy) {
        vacanciesApplied.add(vacancy);
    }

    public void deleteVacancy(Vacancy vacancy) {
        vacanciesApplied.remove(vacancy);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
