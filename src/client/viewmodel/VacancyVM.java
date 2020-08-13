package client.viewmodel;

import client.InternshipClient;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.domain.Company;
import shared.domain.Student;
import shared.domain.Vacancy;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class VacancyVM {

    private InternshipMediator intrnMed;

    private StringProperty description = new SimpleStringProperty();
    private DoubleProperty salary = new SimpleDoubleProperty();
    private InternshipClient client;
    private ObservableList<Student> ols;
    private Vacancy v;

    public VacancyVM(InternshipMediator intrnMed,InternshipClient client, Vacancy v) {
        this.intrnMed = intrnMed;
        this.client = client;
        this.v = v;

        description.setValue(v.getDescription());
        salary.setValue(v.getSalary());

        try {
            ols = FXCollections.observableList(intrnMed.getApplicants(v.getId()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public ObservableList<Student> getStudentList() {
        return ols;
    }

    public boolean isCompany() {
        return client.getLoggedIn() instanceof Company;
    }

    public boolean isStudent() {
        return client.getLoggedIn() instanceof Student;
    }

    public void applyForVacancy(){
        try {
            intrnMed.applyForVacancy(((Student) client.getLoggedIn()).getId(), v.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
