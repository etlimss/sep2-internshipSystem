package client.view;

import client.viewmodel.RegistrationStudentVM;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

public class RegistrationStudentView {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField fName;
    @FXML
    private TextField age;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextArea education;
    @FXML
    private TextArea workingEx;
    @FXML
    private TextArea personalState;
    @FXML
    private TextArea contact;
    private RegistrationStudentVM rsvm;

    private ViewFactory viewFactory;

    public RegistrationStudentView(RegistrationStudentVM rsvm, ViewFactory viewFactory) {
        this.rsvm = rsvm;
        this.viewFactory = viewFactory;
    }

    @FXML
    public void initialize(){
        ToggleGroup tg = new ToggleGroup();
        female.setToggleGroup(tg);
        male.setToggleGroup(tg);
        rsvm.emailProperty().bindBidirectional(email.textProperty());
        rsvm.passwordProperty().bindBidirectional(password.textProperty());
        rsvm.fNameProperty().bindBidirectional(fName.textProperty());
        Bindings.bindBidirectional(age.textProperty(),
                rsvm.ageProperty(),
                new NumberStringConverter());
        rsvm.educationProperty().bindBidirectional(education.textProperty());
        rsvm.workingExProperty().bindBidirectional(workingEx.textProperty());
        rsvm.personalStateProperty().bindBidirectional(personalState.textProperty());
        rsvm.contactProperty().bindBidirectional(contact.textProperty());
        rsvm.femaleProperty().bindBidirectional(female.selectedProperty());
        rsvm.maleProperty().bindBidirectional(male.selectedProperty());
    }
    @FXML
    public void register(){
        rsvm.register();
    }

}
