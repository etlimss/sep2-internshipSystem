package client.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class RegistrationCompanyVM {


    private InternshipMediator intrnMed;
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty compName = new SimpleStringProperty();
    private StringProperty compInfo = new SimpleStringProperty();

    public RegistrationCompanyVM(InternshipMediator intrnMed) {
        this.intrnMed = intrnMed;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getCompName() {
        return compName.get();
    }

    public StringProperty compNameProperty() {
        return compName;
    }

    public String getCompInfo() {
        return compInfo.get();
    }

    public StringProperty compInfoProperty() {
        return compInfo;
    }

    public void register(){
        try {
            intrnMed.registerCompany(email.get(), password.get(),compName.get(),compInfo.get());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
