package client.viewmodel;

import javafx.beans.property.*;
import shared.mediator.InternshipMediator;

import java.rmi.RemoteException;

public class RegistrationStudentVM {


    private InternshipMediator intrnMed;
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty fName = new SimpleStringProperty();
    private IntegerProperty age = new SimpleIntegerProperty();
    private StringProperty education = new SimpleStringProperty();
    private StringProperty workingEx = new SimpleStringProperty();
    private StringProperty personalState = new SimpleStringProperty();
    private StringProperty contact = new SimpleStringProperty();
    private BooleanProperty male = new SimpleBooleanProperty();
    private BooleanProperty female = new SimpleBooleanProperty();

    public RegistrationStudentVM(InternshipMediator intrnMed) {
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

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getEducation() {
        return education.get();
    }

    public StringProperty educationProperty() {
        return education;
    }

    public String getWorkingEx() {
        return workingEx.get();
    }

    public StringProperty workingExProperty() {
        return workingEx;
    }

    public String getPersonalState() {
        return personalState.get();
    }

    public StringProperty personalStateProperty() {
        return personalState;
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public boolean isMale() {
        return male.get();
    }

    public BooleanProperty maleProperty() {
        return male;
    }

    public boolean isFemale() {
        return female.get();
    }

    public BooleanProperty femaleProperty() {
        return female;
    }

    public void register(){
        try {
            if (male.get()){
                intrnMed.registerStudent(email.get(),password.get(),fName.get(),age.get(),'m', education.get(), workingEx.get(),personalState.get(),contact.get());

            }else{
                intrnMed.registerStudent(email.get(),password.get(),fName.get(),age.get(),'f', education.get(), workingEx.get(),personalState.get(),contact.get());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
