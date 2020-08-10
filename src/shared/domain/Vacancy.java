package shared.domain;

import java.util.ArrayList;

public class Vacancy {

    private Long id;

    private String description;
    private double salary;
    private ArrayList<Student> candidates;

    public Vacancy(String description, double salary) {
        this.description = description;
        this.salary = salary;
        candidates = new ArrayList<>();
    }

    public void apply(Student account) {
        candidates.add(account);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Student> getCandidates() {
        return candidates;
    }

    public void addCanditate(Student student) {
        candidates.add(student);
    }

    public void deleteCanditate(Student student) {
        candidates.remove(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
