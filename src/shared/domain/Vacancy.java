package shared.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacancy implements Serializable {

    private Long id;

    private String description;
    private double salary;
    private Long company;
    private ArrayList<Student> candidates;

    public Vacancy(String description, double salary, Long company) {
        this.description = description;
        this.salary = salary;
        this.company = company;
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

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Vacancy ){
            Vacancy other = (Vacancy) obj;
            return id == other.id
                    && description.equals(other.description)
                    && salary == other.salary
                    && candidates.equals(other.candidates);
        }
        else return false;
    }

    @Override
    public String toString() {
        return String.format("vacancy(%d, %s, %f)",id, description, salary);
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }
}