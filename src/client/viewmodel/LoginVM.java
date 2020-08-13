package client.viewmodel;

import client.InternshipClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.domain.Company;
import shared.domain.Student;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class LoginVM {

    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty isStudent = new SimpleBooleanProperty();
    private BooleanProperty isCompany = new SimpleBooleanProperty();
    private InternshipMediator mediator;
    private InternshipClient client;

    public LoginVM(InternshipMediator mediator, InternshipClient client) {
        this.mediator = mediator;
        this.client = client;
    }

    public boolean loginStudent() {
        try {
            Student s = mediator.loginStudent(email.get(), password.get());

            client.setloggedIn(s);

            return s != null;
        } catch(RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginCompany() {
        try {
            Company c = mediator.loginCompany(email.get(), password.get());

            client.setloggedIn(c);

            return c != null;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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

    public boolean isStudent() {
        return isStudent.get();
    }

    public BooleanProperty isStudentProperty() {
        return isStudent;
    }

    public boolean isCompany() {
        return isCompany.get();
    }

    public BooleanProperty isCompanyProperty() {
        return isCompany;
    }
}