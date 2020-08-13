package client.viewmodel;

import client.InternshipClient;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.domain.Company;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class CreateVacancyVM {

    private InternshipMediator intrnMed;
    private StringProperty description = new SimpleStringProperty();
    private DoubleProperty salary = new SimpleDoubleProperty();

    private InternshipClient client;

    public CreateVacancyVM(InternshipMediator intrnMed, InternshipClient client) {
        this.intrnMed = intrnMed;
        this.client = client;
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

    public void uploadVacancy(){
        try {
            Company c = (Company) client.getLoggedIn();
            intrnMed.createVacancy(description.get(), salary.get(), c.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
