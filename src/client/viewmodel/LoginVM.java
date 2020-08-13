package client.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class LoginVM {

    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty isStudent = new SimpleBooleanProperty();
    private BooleanProperty isCompany = new SimpleBooleanProperty();
    private InternshipMediator mediator;

    public LoginVM(InternshipMediator mediator) {
        this.mediator = mediator;
    }

    public void login() {
        try {
            if (isStudent.get()) {
                mediator.loginStudent(email.get(), password.get());
                //open vacancies list

            } else if (isCompany.get()) {
                mediator.loginCompany(email.get(), password.get());
                //open offers list
            }
        }catch (RemoteException e)  {
            System.out.println("Remote exception " + e);
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